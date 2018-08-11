package com.eve.bookmarks.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.dao.BkmkCommandRepository;
import com.eve.bookmarks.dao.BookMarkRepository;
import com.eve.bookmarks.dao.UserRepository;
import com.eve.bookmarks.entitys.BkmkCommand;
import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.BookMarkService;
import com.eve.bookmarks.utils.Constants;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 书签相关操作
 */
@Service
@Transactional
public class BookMarkServiceImpl implements BookMarkService {

    private Logger logger = LoggerFactory.getLogger(BookMarkService.class);

    private final BookMarkRepository bookMarkRepository;

    private final UserRepository userRepository;

    private final MongoTemplate mongoTemplate;

    private final BkmkCommandRepository bkmkCommandRepository;

    @Autowired
    public BookMarkServiceImpl(BookMarkRepository bookMarkRepository, UserRepository userRepository,
                               MongoTemplate mongoTemplate, BkmkCommandRepository bkmkCommandRepository) {
        this.bookMarkRepository = bookMarkRepository;
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
        this.bkmkCommandRepository = bkmkCommandRepository;
    }

    @Override
    public BookMark get(Long mysqlId) {
        return bookMarkRepository.findById(mysqlId).get();
    }

    @Override
    public BookMarkMongo getBookMarktree(String uid) {
        return mongoTemplate.findById(uid, BookMarkMongo.class);
    }

    /**
     * @param node 书签树
     * @param user 用户
     * @description 保存书签，每次保存都是新版本
     */
    @Override
    public void saveBookMarks(Object node, User user) {
        BookMarkMongo bookMarkMongo = new BookMarkMongo(node.toString(), user.getUid(), String.valueOf(user.getVersion()));
        mongoTemplate.save(bookMarkMongo, Constants.BOOK_MARK_MONGODB_NAME);
        //更新user中的mongoid
        user.setMongoId(bookMarkMongo.getId());
        userRepository.save(user);
    }

    @Override
    public Map<String, Object> syncBookMark(String bookmarks, User user) {
        //初始化解析数据
        JSONObject jsonObject = JSONObject.parseObject(bookmarks);
        Long localVersion = Long.valueOf(jsonObject.get("version").toString());
        JSONObject localBkMarks = (JSONObject) jsonObject.get("data");

        Map<String, Object> result = new HashMap<>();

        // 要删除，增加的节点
        List<BkmkCommand> deletes = new ArrayList<>(), adds = new ArrayList<>();

        //本地的书签
        Map<String, BookMark> localMap = new HashMap<>();
        travelAndTransform(localBkMarks, 0, localMap, null);

        //数据库中存储的书签
        Map<String, BookMark> dbMap = new HashMap<>();
        BookMarkMongo bookMarkDB = mongoTemplate.findById(user.getMongoId(), BookMarkMongo.class);
        JSONObject bkmkJsonDb = JSON.parseObject(bookMarkDB.getValue());
        Long dbVersion = Long.valueOf(bookMarkDB.getVersion());

        travelAndTransform(bkmkJsonDb, 0, dbMap, null);

        //首先执行从本地version开始所有命令,0 代表初次导入，不执行命令，直接合并，不做任何删除操作
        if (localVersion != 0) {
            List<BkmkCommand> commandList = bkmkCommandRepository.findCommands(Constants.STATE_ENABLE, localVersion);
            executeCommand(localMap, commandList, deletes, adds);
        }


        //遍历localMap，为了推送给其他同步节点本地新增的东西
        for (String key : localMap.keySet()) {
            if (dbMap.get(key) == null) {
                BookMark bookmark = localMap.get(key);
                createAddCommand(key, bookmark, localVersion, dbVersion);
                addBookMark(bookmark, key, bkmkJsonDb);
            } else {
                //若找到就直接把DBMap中数据移除，剩下的就都是在本地中没有的了
                dbMap.remove(key);
            }
        }

        //遍历dbMap，发现版本树中有而本地没有的，发送删除命令
        for (Map.Entry<String, BookMark> val : dbMap.entrySet()) {
            createDelCommand(val.getKey(), val.getValue(), localVersion, dbVersion);
            delBookMark(val.getKey(), bkmkJsonDb);
        }

        updateBkMark(bkmkJsonDb, user);

        result.put("del", deletes);
        result.put("add", adds);
        return result;
    }

    /**
     * 更新DB中的数据,新增一个版本
     *
     * @param bkmkJsonDb 数据库中的书签树
     */
    private void updateBkMark(JSONObject bkmkJsonDb, User user) {
        user.setVersion(user.getVersion() + 1);
        saveBookMarks(bkmkJsonDb, user);
    }

    /**
     * 往DB对应版本树中插入一条DelCommand
     *
     * @param bookmark     书签明细
     * @param localVersion 浏览器本地书签版本
     * @param dbVersion    数据库中最新版本
     */
    private void createDelCommand(String path, BookMark bookmark, Long localVersion, Long dbVersion) {
        BkmkCommand bkmkCommand = BkmkCommand.buildCommand(path, bookmark, localVersion, dbVersion, -1);
        bkmkCommandRepository.save(bkmkCommand);
    }

    /**
     * 删除DB中指定书签
     *
     * @param bkmkJsonDb 数据库中的书签树
     */
    private void delBookMark(String path, JSONObject bkmkJsonDb) {
        travelAndDel(0, bkmkJsonDb, path, "");
    }


    /**
     * mongo中增加一条书签
     *
     * @param bookmark 书签
     */
    private void addBookMark(BookMark bookmark, String path, JSONObject parentNode) {
        String[] paths = path.split("_");
        travelAndAdd(bookmark, 0, paths, parentNode);
    }

    private void travelAndAdd(BookMark bookmark, int deep, String[] paths, JSONObject parentObj) {
        if (deep > paths.length - 1) {
            return;
        }

        if (parentObj.containsKey("children")) {
            JSONArray arr = (JSONArray) parentObj.get("children");
            boolean isHavePath = false;
            for (Object anArr : arr) {
                JSONObject child = (JSONObject) anArr;
                if (paths[deep].equals(child.get("title")) || paths[deep].equals(child.get("url"))) {
                    isHavePath = true;
                    travelAndAdd(bookmark, deep + 1, paths, parentObj);
                    break;
                }
            }
            if (!isHavePath) {
                arr.add(initDeepPath(deep, paths, bookmark));
            }
        } else {
            JSONArray arr = new JSONArray();
            arr.add(initDeepPath(deep, paths, bookmark));
            parentObj.put("children", arr);
        }

    }

    /**
     * 检测到从某层级开始创建路径
     *
     * @param deep     层级
     * @param paths    路径
     * @param bookmark 书签
     * @return 一条树路径节点列表
     */
    private JSONObject initDeepPath(int deep, String[] paths, BookMark bookmark) {
        JSONObject parentObj = new JSONObject();
        JSONObject rootNode = parentObj;
        for (int i = deep; i < paths.length; i++) {
            if (i == paths.length - 1) {
                JSONArray arr = (JSONArray) parentObj.get("children");
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("title", bookmark.getTitle());
                if (Strings.isNotBlank(bookmark.getUrl())) {
                    jsonObject1.put("url", bookmark.getUrl());
                } else {
                    jsonObject1.put("children", new JSONArray());
                }
                arr.add(jsonObject1);
            } else {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("title", paths[i]);

                jsonObject1.put("children", new JSONArray());
                JSONArray arr = (JSONArray) parentObj.get("children");
                arr.add(jsonObject1);
                parentObj = jsonObject1;
            }
        }
        return rootNode;
    }

    /**
     * 同步时，本地增加书签，需要往版本树增加一个addCommand
     *
     * @param bookmark 书签
     */
    private void createAddCommand(String path, BookMark bookmark, Long localVersion, Long dbVersion) {
        BkmkCommand bkmkCommand = BkmkCommand.buildCommand(path, bookmark, localVersion, dbVersion, 1);
        bkmkCommandRepository.save(bkmkCommand);
    }

    /**
     * 执行命令
     *
     * @param localMap 本地树结构
     * @param commands 待执行命令
     * @param dels     删除命令集合
     * @param adds     增加命令集合
     */
    private void executeCommand(Map<String, BookMark> localMap, List<BkmkCommand> commands, List<BkmkCommand> dels, List<BkmkCommand> adds) {
        for (BkmkCommand bkmkCommand : commands) {

            if (bkmkCommand.getCommand() == Constants.STATE_ENABLE) {
                adds.add(bkmkCommand);
                localMap.put(bkmkCommand.getPath(), bkmkCommand.getBookMark());
            } else {
                dels.add(bkmkCommand);
                localMap.remove(bkmkCommand.getPath());
            }
        }
    }

    /**
     * 更新书签
     *
     * @param node 书签树
     * @param user 用户
     */
    @Override
    public void updateBookMarks(Object node, User user) {
        BookMarkMongo bookMarkMongo = new BookMarkMongo(node.toString(), user.getUid(), String.valueOf(user.getVersion() + 1));

        //重新插入书签树
        mongoTemplate.save(bookMarkMongo, Constants.BOOK_MARK_MONGODB_NAME);
        //更新user表版本号
        userRepository.addVersion(user.getId(), bookMarkMongo.getId());
    }

    /**
     * 遍历树，并将节点存储到map中去
     *
     * @param jsonObject 节点树
     */
    public void travelAndTransform(JSONObject jsonObject, int deep, Map<String, BookMark> map, String path) {
        if (jsonObject.containsKey("children")) {
            JSONArray arr = (JSONArray) jsonObject.get("children");
            for (Object anArr : arr) {
                JSONObject child = (JSONObject) anArr;
                String nextPath = path + "_" + (child.get("url") == null ? child.get("title").toString() : child.get("url").toString());
                travelAndTransform(child, deep + 1, map, nextPath);
            }
        }
        logger.debug("node属性：", jsonObject.toJSONString());
        BookMark book = jsonObject.toJavaObject(BookMark.class);
        map.put(path, book);
    }

    /**
     * 遍历树，并将指定节点删除
     *
     * @param parentObj 父级节点
     */
    public void travelAndDel(int deep, JSONObject parentObj, String targetKey, String parentPath) {
        if (parentObj.containsKey("children")) {
            JSONArray arr = (JSONArray) parentObj.get("children");
            for (int i = 0; i < arr.size(); i++) {
                JSONObject child = (JSONObject) arr.get(i);
                String nextPath = parentPath + "_" + (child.get("url") == null ? child.get("title").toString() : child.get("url").toString());
                if (nextPath.equals(targetKey)) {
                    arr.remove(child);
                    return;
                }
                travelAndDel(deep + 1, child, targetKey, nextPath);
            }
        }
    }
//
//    /**
//     * 用深度，url（书签）或者标题(目录)，父级标题 做唯一标识
//     *
//     * @param book 当前书签
//     * @param deep 层级
//     * @return 书签的路径组成的key
//     */
//    private String buildKey(BookMark book, JSONObject parentBook, int deep) {
//        String parentTitle = parentBook == null ? "" : "_" + parentBook.getString("title");
//        String key = "" + deep + parentTitle;
//        if (!StringUtils.isNullOrEmpty(book.getUrl())) {
//            key += "_" + book.getUrl();
//        } else {
//            key += "_" + book.getTitle();
//        }
//        return key;
//    }

    @Override
    public void saveBookMark(BookMark book) {
        bookMarkRepository.save(book);
    }
}
