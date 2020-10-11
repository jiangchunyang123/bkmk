package com.eve.bookmarks.book.dao;

import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void books() {

        bookMapper.selectByMap(Maps.newHashMap("1","2"));
    }

    @Test
    public void booksCount() {
        bookMapper.booksCount("hello","");
    }
}