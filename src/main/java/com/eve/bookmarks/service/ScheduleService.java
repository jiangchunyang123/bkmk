package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.po.Schedule;
import com.eve.bookmarks.entitys.po.ScheduleRecord;
import com.eve.bookmarks.entitys.vo.MailMsg;
import com.eve.bookmarks.entitys.vo.QueryParam;
import com.eve.bookmarks.entitys.vo.ScheduleVo;

import java.util.List;

public interface ScheduleService {
    void insert(Schedule schedule);

    void appendRecord(Schedule schedule);

    List<Schedule> queryList(Schedule schedule);

    MailMsg queryDeadLineSchs();

    List<ScheduleRecord> queryRecordList(QueryParam record);

    Schedule findById(Long id);

    void updateRecord(ScheduleVo record);

    void update(Schedule schedule);

    void delete(Long id);

    void deleteScd(Long id);
}
