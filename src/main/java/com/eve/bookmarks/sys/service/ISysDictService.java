package com.eve.bookmarks.sys.service;

import com.eve.bookmarks.sys.domain.SysDict;

import java.util.List;

public interface ISysDictService {

    SysDict get(String type, String code);

    List<SysDict> dicts(String text);
}
