package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;

import java.util.List;

public interface ScheduleService {
    void insert(Schedule schedule);

    void appendRecord(Schedule schedule);

    List<Schedule> queryList(Schedule schedule);

    List<ScheduleRecord> queryRecordList(ScheduleRecord record);

    Schedule findById(Long id);

}
