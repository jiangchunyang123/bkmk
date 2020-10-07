package com.eve.bookmarks.schedule.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eve.bookmarks.schedule.domain.po.Schedule;
import com.eve.bookmarks.schedule.domain.po.ScheduleRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRecordMapper extends BaseMapper<ScheduleRecord> {

    ScheduleRecord findFirstByScheduleOrderByIdDesc(Schedule schedule);

    Integer updateRecordState(@Param(value = "state") int state, @Param(value = "id")Long id);


    List<ScheduleRecord> querySchRecordList(@Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime, @Param("userId") Long userid);

    List<ScheduleRecord> queryAllByDeadLineBeforeAndStateEquals(LocalDateTime dealLine,int state);

    List<ScheduleRecord> queryAllByDeadLineBetweenAndStateEquals(LocalDateTime startTime,LocalDateTime endTime,int state);
}