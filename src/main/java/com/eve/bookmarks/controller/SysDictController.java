package com.eve.bookmarks.controller;

import com.eve.bookmarks.book.service.ISysDictService;
import com.eve.bookmarks.entitys.po.SysDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/dicts")
@RestController
public class SysDictController {

    private ISysDictService sysDictService;

    @Autowired
    public SysDictController(ISysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }


    @GetMapping
    public List<SysDict> sysDicts() {
        return sysDictService.dicts();
    }
}
