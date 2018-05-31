package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.BookMarkRepository;
import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    private BookMarkRepository bookMarkRepository;

    @Override
    public BookMark get(Long mysqlId) {
        return bookMarkRepository.findById(mysqlId).get();
    }
}
