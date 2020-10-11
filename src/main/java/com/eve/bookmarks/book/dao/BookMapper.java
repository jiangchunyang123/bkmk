package com.eve.bookmarks.book.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eve.bookmarks.book.domain.po.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BookMapper继承基类
 */
public interface BookMapper extends BaseMapper<Book> {

    void updateByPrimaryKeySelective(Book book);

    void deleteByPrimaryKey(Integer id);

    Book selectByPrimaryKey(Integer id);

    List<Book> books(@Param("name") String name,
                     @Param("barCode")String barCode,
                     @Param("size") Integer size,
                     @Param("offset") Integer offset);

    Integer booksCount(@Param("name") String name,@Param("barCode")String barCode);
}