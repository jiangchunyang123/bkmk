package com.eve.bookmarks.book.service;

import com.eve.bookmarks.book.dao.BookMapper;
import com.eve.bookmarks.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public void saveOrUpdate(Book book) {
        if (book.getId() != null) {
            bookMapper.updateByPrimaryKeySelective(book);
        } else {
            bookMapper.insert(book);
        }
    }

    @Override
    public void delete(Integer id) {
        bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Book> search(String name) {
        return null;
    }
}

