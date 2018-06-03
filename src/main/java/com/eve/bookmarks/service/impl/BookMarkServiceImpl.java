package com.eve.bookmarks.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.dao.BookMarkRepository;
import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.service.BookMarkService;
import com.eve.bookmarks.utils.Constants;
import com.eve.bookmarks.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * 书签相关操作
 */
@Service
@Transactional
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    private BookMarkRepository bookMarkRepository;
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
    public void saveBookMarks(Object node, String uid) {
        nodeToListModel(node,uid);
        BookMarkMongo bookMarkMongo = new BookMarkMongo(node.toString());
        mongoTemplate.save(bookMarkMongo,Constants.BOOK_MARK_MONGODB_NAME);
        System.out.println("mongodb生成id:"+bookMarkMongo.getId());

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
            BookMark book = obj.toJavaObject(BookMark.class);
            Date date = new Date();
            book.setCrateTime(date.getTime());
            book.setUpdateTime(date.getTime());
            book.setUid(uid);
            bookMarkRepository.save(book);
        }

    }
    @Override
    public  void saveBookMark(BookMark book) {
         bookMarkRepository.save(book);
    }
}
