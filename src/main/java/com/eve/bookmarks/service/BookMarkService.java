package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.User;

import java.util.Map;

public interface BookMarkService {

    BookMark get(Long mysqlId);

    void updateBookMarks(Object node, User user);

    void saveBookMark(BookMark book);

    BookMarkMongo getBookMarktree(String uid);

    void saveBookMarks(Object node, User uid);

    Map<String, Object> syncBookMark(String data, User user );
}
