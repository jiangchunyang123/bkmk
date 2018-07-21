package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.BkmkCommand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BkmkCommandRepository extends CrudRepository<BkmkCommand, Long> {
    /**
     * 查询所有version范围包含自己version的所有命令
     * @param state
     * @param version
     * @return
     */
    @Query("from BkmkCommand  cmd where cmd.startVersion <= :version and cmd.endVersion >=:version and state = 1 order by cmd.createTime")
    List<BkmkCommand> findCommands(@Param("state") int state,@Param("version") Long version);

}
