package com.eve.bookmarks.book.controller;

import com.eve.bookmarks.book.domain.po.Book;
import com.eve.bookmarks.book.service.IBookService;
import com.eve.bookmarks.common.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final IBookService bookService;


    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Result saveOrUpdate(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
        return Result.Success();
    }

    @GetMapping
    public Result search(@RequestParam(value = "name", required = false) String name,
                         @RequestParam Integer pageSize,
                         @RequestParam Integer pageNum) {
        return Result.Success(bookService.search(name, pageSize, pageNum));
    }
}
