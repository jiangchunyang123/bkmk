package com.eve.bookmarks.bookmark.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eve.bookmarks.bookmark.domain.BkmkCommand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BkmkCommandMapper extends BaseMapper<BkmkCommand> {
    List<BkmkCommand> findCommands(@Param("state") int state, @Param("version") Long version);


    int save(BkmkCommand bkmkCommand);
}