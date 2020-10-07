package com.eve.bookmarks.book.service;

import com.eve.bookmarks.sys.dao.SysDictMapper;
import com.eve.bookmarks.sys.domain.SysDict;
import com.eve.bookmarks.sys.domain.SysDictExample;
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

    @Override
    public SysDict get(String type, String code) {
        SysDictExample sysDictExample = new SysDictExample();
        sysDictExample.createCriteria().andTypeEqualTo(type).andCodeEqualTo(code);
        List<SysDict> sysDicts = sysDictMapper.selectByExample(sysDictExample);
        return sysDicts != null && !sysDicts.isEmpty() ? sysDicts.get(0) : null;
    }
}
