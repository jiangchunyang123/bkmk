package com.eve.bookmarks.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.dao.BookMarkRepository;
import com.eve.bookmarks.dao.UserRepository;
import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.BookMarkService;
import com.eve.bookmarks.utils.Constants;
import com.eve.bookmarks.utils.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

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


    @Override
    public BookMark get(Long mysqlId) {
        return bookMarkRepository.findById(mysqlId).get();
    }
    @Override
    public BookMarkMongo getBookMarktree(String uid) {
        return mongoTemplate.findById(uid,BookMarkMongo.class);
    }
    @Override
    public void saveBookMarks(Object node, User user) {
//        nodeToListModel(node,uid);
        BookMarkMongo bookMarkMongo = new BookMarkMongo(node.toString(),user.getUid(),String.valueOf(user.getVersion()));
        mongoTemplate.save(bookMarkMongo,Constants.BOOK_MARK_MONGODB_NAME);
    }

    /**
     * 更新书签
     * @param node 书签树
     * @param user 用户
     */
    @Override
    public void updateBookMarks(Object node, User user) {
        BookMarkMongo bookMarkMongo = new BookMarkMongo(node.toString(),user.getUid(),String.valueOf(user.getVersion()+1));

        //重新插入书签树
        mongoTemplate.save(bookMarkMongo,Constants.BOOK_MARK_MONGODB_NAME );
        //更新user表版本号
        userRepository.addVersion(user.getId(),bookMarkMongo.getId());
    }

    /**
     * 保存属性jsonObject
     *
     * @param nodes
     * @return
     */
    public void nodeToListModel(Object nodes ,String uid) {
        if (nodes instanceof JSONArray) {
            JSONArray arr = (JSONArray) nodes;
            for (int i = 0; i < arr.size(); i++) {
                Object obj = arr.get(i);
                nodeToListModel(obj,uid);
            }
        } else if (nodes instanceof JSONObject) {
            JSONObject obj = (JSONObject) nodes;
            if (obj.containsKey("children")) {
                nodeToListModel(obj.get("children"),uid);
            }
            logger.debug("node属性：",obj.toJSONString());
            BookMark book = obj.toJavaObject(BookMark.class);
            LocalDateTime date =LocalDateTime.now();
            book.setCrateTime(date);
            book.setUpdateTime(date);
            book.setUid(uid);
            bookMarkRepository.save(book);
        }

    }
    @Override
    public  void saveBookMark(BookMark book) {
         bookMarkRepository.save(book);
    }
}
