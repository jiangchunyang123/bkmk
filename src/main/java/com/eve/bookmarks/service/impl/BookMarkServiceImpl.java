package com.eve.bookmarks.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.dao.BkmkCommandMapper;
import com.eve.bookmarks.dao.BookMarkMapper;
import com.eve.bookmarks.dao.UserMapper;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.po.BkmkCommand;
import com.eve.bookmarks.entitys.po.BookMark;
import com.eve.bookmarks.entitys.po.User;
import com.eve.bookmarks.service.BookMarkService;
import com.eve.bookmarks.utils.BookMkTreeBuilder;
import com.eve.bookmarks.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final BookMarkMapper bookMarkMapper;

    private final UserMapper userMapper;

    private final MongoTemplate mongoTemplate;

    private final BkmkCommandMapper bkmkCommandMapper;

    @Autowired
    public BookMarkServiceImpl(BookMarkMapper bookMarkMapper, UserMapper userMapper,
                               MongoTemplate mongoTemplate, BkmkCommandMapper bkmkCommandMapper) {
        this.bookMarkMapper = bookMarkMapper;
        this.userMapper = userMapper;
        this.mongoTemplate = mongoTemplate;
        this.bkmkCommandMapper = bkmkCommandMapper;
    }

    @Override
    public BookMark get(Long mysqlId) {
        return bookMarkMapper.selectByPrimaryKey(mysqlId);
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
    public void saveBookMarks(JSONArray node, User user) {
        BookMarkMongo bookMarkMongo = new BookMarkMongo(node.toString(), user.getUserName(), user.getVersion());
        mongoTemplate.save(bookMarkMongo, Constants.BOOK_MARK_MONGODB_NAME);
        //更新user中的mongoid
        user.setMongoId(bookMarkMongo.getId());
        userMapper.insert(user);
    }

    @Override
    public Map<String, Object> syncBookMark(String bookmarks, Long localVersion, User user) {
        //初始化解析数据
        JSONArray localBkMarks = JSONObject.parseArray(bookmarks);

        Map<String, Object> result = new HashMap<>();

        // 要删除，增加的节点
        List<BkmkCommand> deletes = new ArrayList<>();
        List<BkmkCommand> adds = new ArrayList<>();

        //本地的书签
        Map<String, BookMark> localMap = new HashMap<>();
        BookMkTreeBuilder.travelAndTransform(localBkMarks, 0, localMap, "");

        //数据库中存储的书签
        Map<String, BookMark> dbMap = new HashMap<>();
        JSONArray bkmkJsonDb = null;
        Long dbVersion = null;
        if(user.getMongoId()!=null){
            BookMarkMongo bookMarkDB = mongoTemplate.findById(user.getMongoId(), BookMarkMongo.class);

            bkmkJsonDb = JSON.parseArray(bookMarkDB.getValue());
            dbVersion = bookMarkDB.getVersion();
            if(bkmkJsonDb!=null){
                BookMkTreeBuilder.travelAndTransform(bkmkJsonDb, 0, dbMap, "");
            }
        } 
        if(bkmkJsonDb ==null){
            bkmkJsonDb = new JSONArray();
            dbVersion = 0L;
        }

        //首先执行从本地version开始所有命令,0 代表初次导入，不执行命令，直接合并，不做任何删除操作
        if (localVersion != 0) {
            List<BkmkCommand> commandList = bkmkCommandMapper.findCommands(Constants.STATE_ENABLE, localVersion);
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
    private void updateBkMark(JSONArray bkmkJsonDb, User user) {
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
        bkmkCommandMapper.insert(bkmkCommand);
    }

    /**
     * 删除DB中指定书签
     *
     * @param bkmkJsonDb 数据库中的书签树
     */
    private void delBookMark(String path, JSONArray bkmkJsonDb) {
        BookMkTreeBuilder.travelAndDel(0, bkmkJsonDb, path, "");
    }


    /**
     * mongo中增加一条书签
     *
     * @param bookmark 书签
     */
    private void addBookMark(BookMark bookmark, String path, JSONArray parentNode) {
        String[] paths = path.split("_");
        BookMkTreeBuilder.travelAndAdd(bookmark, 0, paths, parentNode);
    }

    /**
     * 同步时，本地增加书签，需要往版本树增加一个addCommand
     *
     * @param bookmark 书签
     */
    private void createAddCommand(String path, BookMark bookmark, Long localVersion, Long dbVersion) {
        BkmkCommand bkmkCommand = BkmkCommand.buildCommand(path, bookmark, localVersion, dbVersion, 1);
        bkmkCommandMapper.save(bkmkCommand);
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

}
