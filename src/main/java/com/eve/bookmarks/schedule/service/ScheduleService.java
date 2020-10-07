package com.eve.bookmarks.schedule.service;

import com.eve.bookmarks.common.domain.vo.MailMsg;
import com.eve.bookmarks.common.domain.vo.QueryParam;
import com.eve.bookmarks.schedule.domain.po.Schedule;
import com.eve.bookmarks.schedule.domain.po.ScheduleRecord;
import com.eve.bookmarks.schedule.domain.vo.ScheduleVo;

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
