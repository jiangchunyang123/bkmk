package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRecordRepository extends CrudRepository<ScheduleRecord, Long> {
    List<ScheduleRecord> findAllByUser(User user);

    ScheduleRecord findFirstByScheduleOrderByIdDesc(Schedule schedule);
}
