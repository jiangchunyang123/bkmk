package com.eve.bookmarks.bookmark.service;

import com.alibaba.fastjson.JSONArray;
import com.eve.bookmarks.bookmark.domain.BookMark;
import com.eve.bookmarks.bookmark.domain.BookMarkMongo;
import com.eve.bookmarks.sys.domain.User;

import java.util.Map;

public interface BookMarkService {

    BookMark get(Long mysqlId);

    BookMarkMongo getBookMarktree(String uid);

    void saveBookMarks(JSONArray node, User uid);

    Map<String, Object> syncBookMark(String data,Long version, User user );
}
