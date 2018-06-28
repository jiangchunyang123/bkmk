package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.ScheduleRecordRepository;
import com.eve.bookmarks.dao.ScheduleRepository;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.ScheduleService;
import com.eve.bookmarks.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
@Autowired
private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleRecordRepository scheduleRecordRepository;

    @Override
    public void insert(Schedule schedule) {
        schedule.setCreateTimeMils(DateUtils.nowMils());
        scheduleRepository.save(schedule);

        //创建时自动创建一个代办
        ScheduleRecord record = scheduleRecordRepository.findFirstByScheduleOrderByIdDesc(schedule);
        ScheduleRecord next =schedule.builderNextRecord(record);
        scheduleRecordRepository.save(next);
    }

    /**
     * 增加record
     * @param schedule
     */
    @Override
    public void appendRecord(Schedule schedule) {

    }

    @Override
    public List<Schedule> queryList(Schedule schedule) {
      return  scheduleRepository.findAllByUserId(schedule.getUser().getId());
    }
    @Override
    public List<ScheduleRecord> queryRecordList(ScheduleRecord record) {
        return  scheduleRecordRepository.findAllByUser(record.getUser());
    }
    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).get();
    }
}
