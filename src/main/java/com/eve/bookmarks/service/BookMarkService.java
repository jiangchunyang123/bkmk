package com.eve.bookmarks.service;

import com.alibaba.fastjson.JSONArray;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.po.BookMark;
import com.eve.bookmarks.entitys.po.User;

import java.util.Map;

public interface BookMarkService {

    BookMark get(Long mysqlId);

    BookMarkMongo getBookMarktree(String uid);

    void saveBookMarks(JSONArray node, User uid);

    Map<String, Object> syncBookMark(String data,Long version, User user );
}
