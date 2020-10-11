package com.eve.bookmarks.book.service;

import com.eve.bookmarks.book.domain.po.Book;
import org.springframework.data.domain.Page;

public interface IBookService {

    void saveOrUpdate(Book book);

    void delete(Integer id);

    Page<Book> search(String name,String barCode, Integer pageSize, Integer PageNum);
}
