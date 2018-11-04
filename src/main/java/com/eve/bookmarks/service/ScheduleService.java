package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.vo.QueryParam;
import com.eve.bookmarks.entitys.vo.ScheduleVo;

import java.util.List;

public interface ScheduleService {
    void insert(Schedule schedule);

    void appendRecord(Schedule schedule);

    List<Schedule> queryList(Schedule schedule);

    List<ScheduleRecord> queryRecordList(QueryParam record);

    Schedule findById(Long id);

    void updateRecord(ScheduleVo record);

}
