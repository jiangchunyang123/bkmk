package com.eve.bookmarks.book.service;

import com.eve.bookmarks.book.domain.Book;

import java.util.List;

public interface IBookService {

    void saveOrUpdate(Book book);

    void delete(Integer id);

    List<Book> search(String name);
}
