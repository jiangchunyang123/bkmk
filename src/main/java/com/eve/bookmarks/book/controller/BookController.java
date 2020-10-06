package com.eve.bookmarks.book.controller;

import com.eve.bookmarks.book.domain.Book;
import com.eve.bookmarks.book.service.IBookService;
import com.eve.bookmarks.entitys.Result;
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
    public Result search(@RequestParam("name") String name) {
        return Result.Success(bookService.search(name));
    }
}
