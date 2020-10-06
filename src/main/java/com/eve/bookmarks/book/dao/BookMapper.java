package com.eve.bookmarks.book.dao;


import com.eve.bookmarks.book.domain.Book;
import org.springframework.stereotype.Repository;

/**
 * BookMapper继承基类
 */
@Repository
public interface BookMapper {

    void insert(Book book);

    void updateByPrimaryKeySelective(Book book);

    void deleteByPrimaryKey(Integer id);

    Book selectByPrimaryKey(Integer id);


}