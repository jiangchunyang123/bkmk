package com.eve.bookmarks.book.service;

import com.eve.bookmarks.sys.domain.SysDict;

import java.util.List;

public interface ISysDictService {

    List<SysDict> dicts();

    SysDict get(String type, String code);
}
