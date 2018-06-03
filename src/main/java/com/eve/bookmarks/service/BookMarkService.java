package com.eve.bookmarks.service;

import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.BookMarkMongo;

public interface BookMarkService {

    BookMark get(Long mysqlId);

    void saveBookMark(BookMark book);

    BookMarkMongo getBookMarktree(String uid);

    void saveBookMarks(Object node, String uid);
}
