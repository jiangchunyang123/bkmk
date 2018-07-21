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
import com.mysql.jdbc.StringUtils;
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

    @Autowired
    private BookMarkRepository bookMarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BkmkCommandRepository bkmkCommandRepository;

    @Override
    public BookMark get(Long mysqlId) {
        return bookMarkRepository.findById(mysqlId).get();
    }

    @Override
    public BookMarkMongo getBookMarktree(String uid) {
        return mongoTemplate.findById(uid, BookMarkMongo.class);
    }

    @Override
    public void saveBookMarks(Object node, User user) {
//        travel(node,uid);
        BookMarkMongo bookMarkMongo = new BookMarkMongo(node.toString(), user.getUid(), String.valueOf(user.getVersion()));
        mongoTemplate.save(bookMarkMongo, Constants.BOOK_MARK_MONGODB_NAME);
    }

    @Override
    public Map<String, Object> syncBookMark(String bookmarks, User user ) {
        //初始化解析数据
        JSONObject jsonObject = JSONObject.parseObject(bookmarks);
        Long localVersion =  Long.valueOf(jsonObject.get("version").toString());
        JSONObject localBkMarks = (JSONObject) jsonObject.get("data");

        Map<String, Object> result = new HashMap<>();

        // 要删除，增加的节点
        List<BkmkCommand> deletes = new ArrayList<>(), adds = new ArrayList<>();

        //本地的书签
        Map<String, BookMark> localMap = new HashMap<>();
        travel(localBkMarks, 0, localMap, null);

        //数据库中存储的书签
        Map<String, BookMark> dbMap = new HashMap<>();
        BookMarkMongo bookMarkDB = mongoTemplate.findById(user.getMongoId(), BookMarkMongo.class);
        JSONObject bkmkJsonDb = JSON.parseObject(bookMarkDB.getValue());
        travel(bkmkJsonDb, 0, dbMap, null);

        //首先执行从本地version开始所有命令,0 代表初次导入，不执行命令，直接合并，不做任何删除操作
        if (localVersion != 0) {
            List<BkmkCommand> commandList = bkmkCommandRepository.findCommands(Constants.STATE_ENABLE, localVersion);
            executeCommand(localMap, commandList,deletes,adds);
        }


        //遍历localMap，为了推送给其他同步节点本地新增的东西
        for (String key : localMap.keySet()) {
            if (dbMap.get(key) == null) {
                BookMark bookmark = localMap.get(key);
                createAddCommand(bookmark);
                addBookMark(bookmark, bkmkJsonDb);
            } else {
                //若找到就直接把DBMap中数据移除，剩下的就都是在本地中没有的了
                dbMap.remove(key);
            }
        }

        //遍历dbMap，发现版本树中有而本地没有的，发送删除命令
        for (BookMark val : dbMap.values()) {
            createDelCommand(val);
            delBookMark(val, bkmkJsonDb);
        }

        result.put("del", deletes);
        result.put("add", adds);
        return result;
    }

    /**
     * 删除DB中指定书签
     *
     * @param bookmark
     * @param bkmkJsonDb
     */
    private void delBookMark(BookMark bookmark, JSONObject bkmkJsonDb) {
    }

    /**
     * 往DB对应版本树中插入一条DelCommand
     *
     * @param bookmark
     */
    private void createDelCommand(BookMark bookmark) {

    }

    /**
     * mongo中增加一条书签
     *
     * @param bookmark
     */
    private void addBookMark(BookMark bookmark, JSONObject jsonObject) {

    }

    /**
     * 同步时，本地增加书签，需要往版本树增加一个addCommand
     *
     * @param bookmark
     */
    private void createAddCommand(BookMark bookmark) {

    }

    private void executeCommand(Map<String, BookMark> localMap, List<BkmkCommand> commands,  List<BkmkCommand> dels, List<BkmkCommand> adds) {

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
     * 保存属性jsonObject
     *
     * @param nodes
     * @return
     */
    public void travel(JSON nodes, int deep, Map<String, BookMark> map, JSONObject parentObj) {
        JSONObject jsonObject = (JSONObject) nodes;
        if (jsonObject.containsKey("children")) {
            JSONArray arr = (JSONArray) jsonObject.get("children");
            for (int i = 0; i < arr.size(); i++) {
                JSONObject child = (JSONObject) arr.get(i);
                travel(child, deep + 1, map, jsonObject);
            }
        }
        logger.debug("node属性：", jsonObject.toJSONString());
        BookMark book = jsonObject.toJavaObject(BookMark.class);
        String key = buildKey(book, parentObj, deep);
        map.put(key, book);
    }

    /**
     * 用深度，url（书签）或者标题(目录)，父级标题 做唯一标识
     *
     * @param book
     * @param deep
     * @return
     */
    private String buildKey(BookMark book, JSONObject parentBook, int deep) {
        String key = "" + deep + "_" + parentBook.getString("title");
        if (!StringUtils.isNullOrEmpty(book.getUrl())) {
            key += "_" + book.getUrl();
        } else {
            key += "_" + book.getTitle();
        }
        return key;
    }

    @Override
    public void saveBookMark(BookMark book) {
        bookMarkRepository.save(book);
    }
}
