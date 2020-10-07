package com.eve.bookmarks.bookmark.dao;

import com.eve.bookmarks.bookmark.domain.BookMark;
import com.eve.bookmarks.bookmark.domain.BookMarkExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMarkMapper {
    long countByExample(BookMarkExample example);

    int deleteByExample(BookMarkExample example);

    int deleteByPrimaryKey(Long mysqlId);

    int insert(BookMark record);

    int insertSelective(BookMark record);

    List<BookMark> selectByExample(BookMarkExample example);

    BookMark selectByPrimaryKey(Long mysqlId);

    int updateByExampleSelective(@Param("record") BookMark record, @Param("example") BookMarkExample example);

    int updateByExample(@Param("record") BookMark record, @Param("example") BookMarkExample example);

    int updateByPrimaryKeySelective(BookMark record);

    int updateByPrimaryKey(BookMark record);
}