package com.eve.bookmarks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eve.bookmarks.entitys.po.Schedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleMapper extends BaseMapper<Schedule> {

}