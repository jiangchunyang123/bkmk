package com.eve.bookmarks.sys.service.impl;

import com.eve.bookmarks.sys.dao.SysDictMapper;
import com.eve.bookmarks.sys.domain.SysDict;
import com.eve.bookmarks.sys.domain.SysDictExample;
import com.eve.bookmarks.sys.service.ISysDictService;
import org.apache.logging.log4j.util.Strings;
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
    public List<SysDict> dicts(String text) {
        SysDictExample sysDictExample = new SysDictExample();
        sysDictExample.setOrderByClause("type");
        if(Strings.isNotBlank(text)){
            sysDictExample.createCriteria().andValueLike(text);
        }
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
