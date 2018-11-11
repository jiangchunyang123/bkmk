package com.eve.bookmarks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eve.bookmarks.entitys.po.Schedule;
import com.eve.bookmarks.entitys.po.ScheduleRecord;
import com.eve.bookmarks.entitys.po.ScheduleRecordExample;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRecordMapper extends BaseMapper<ScheduleRecord> {

    ScheduleRecord findFirstByScheduleOrderByIdDesc(Schedule schedule);

    Integer updateRecordState(@Param(value = "state") int state, @Param(value = "id")Long id);


    List<ScheduleRecord> querySchRecordList(@Param("startTime") LocalDateTime startTime, @Param("endTime")LocalDateTime endTime, @Param("userId")Long userid );

    List<ScheduleRecord> queryAllByDeadLineBeforeAndStateEquals(LocalDateTime dealLine,int state);

    List<ScheduleRecord> queryAllByDeadLineBetweenAndStateEquals(LocalDateTime startTime,LocalDateTime endTime,int state);
}