package com.eve.bookmarks.book.service;

import com.eve.bookmarks.dao.SysDictMapper;
import com.eve.bookmarks.entitys.po.SysDict;
import com.eve.bookmarks.entitys.po.SysDictExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictServiceImpl implements ISysDictService {

    private final SysDictMapper sysDictMapper;

    @Autowired
    public SysDictServiceImpl(SysDictMapper sysDictMapper) {
        this.sysDictMapper = sysDictMapper;
    }

    @Override
    public List<SysDict> dicts() {
        SysDictExample sysDictExample = new SysDictExample();
        sysDictExample.setOrderByClause("type");
        return sysDictMapper.selectByExample(sysDictExample);
    }
}
