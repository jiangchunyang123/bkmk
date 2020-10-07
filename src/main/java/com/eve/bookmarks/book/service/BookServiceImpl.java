package com.eve.bookmarks.book.service;

import com.eve.bookmarks.book.dao.BookMapper;
import com.eve.bookmarks.book.domain.po.Book;
import com.eve.bookmarks.sys.domain.SysDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.eve.bookmarks.common.utils.Constants.BOOK_PUBLISHER_TYPE;

@Service
public class BookServiceImpl implements IBookService {

    private final BookMapper bookMapper;

    private final ISysDictService sysDictService;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, ISysDictService sysDictService) {
        this.bookMapper = bookMapper;
        this.sysDictService = sysDictService;
    }

    @Override
    public void saveOrUpdate(Book book) {
        if (book.getId() != null) {
            bookMapper.updateByPrimaryKeySelective(book);
        } else {
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("barCode", book.getBarCode());
            List<Book> books = bookMapper.selectByMap(stringObjectMap);
            if (books != null && !books.isEmpty()) {
                throw new RuntimeException("该条形码已存在！");
            }
            book.setCreateTime(new Date());
            bookMapper.insert(book);
        }
    }

    @Override
    public void delete(Integer id) {
        bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page<Book> search(String name, Integer pageSize, Integer pageNum) {
        Integer count = bookMapper.booksCount(name);
        List<Book> books = bookMapper.books(name, pageSize, pageNum * pageSize);
        books.forEach((book) -> {
            SysDict sysDict = sysDictService.get(BOOK_PUBLISHER_TYPE, book.getPublisher());
            book.setPublisherName(Optional.ofNullable(sysDict).orElse(new SysDict()).getValue());
        });
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return new PageImpl<>(books, pageable, count);
    }
}

