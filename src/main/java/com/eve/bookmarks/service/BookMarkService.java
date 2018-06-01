package com.eve.bookmarks.service;

import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.BookMark;

public interface BookMarkService {

    BookMark get(Long mysqlId);

    void saveBookMark(BookMark book);

    void saveBookMarks(Object node, String uid);
}
