package com.eve.bookmarks.sys.controller;

import com.eve.bookmarks.sys.domain.SysDict;
import com.eve.bookmarks.sys.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<SysDict> sysDicts(@RequestParam(value = "q",required = false)String text) {
        return sysDictService.dicts(text);
    }
}
