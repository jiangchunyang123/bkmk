package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.ScheduleRecordRepository;
import com.eve.bookmarks.dao.ScheduleRepository;
import com.eve.bookmarks.dao.UserRepository;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.vo.MailMsg;
import com.eve.bookmarks.entitys.vo.QueryParam;
import com.eve.bookmarks.entitys.vo.ScheduleVo;
import com.eve.bookmarks.service.ScheduleService;
import com.eve.bookmarks.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleRecordRepository scheduleRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert(Schedule schedule) {
        schedule.setCreateTime(LocalDateTime.now());
        scheduleRepository.save(schedule);
        appendRecord(schedule);
    }

    /**
     * 增加record
     *
     * @param schedule
     */
    @Override
    public void appendRecord(Schedule schedule) {
        //创建时自动创建一个代办
        ScheduleRecord record = scheduleRecordRepository.findFirstByScheduleOrderByIdDesc(schedule);
        ScheduleRecord next = schedule.builderNextRecord(record);
        scheduleRecordRepository.save(next);
    }

    @Override
    public List<Schedule> queryList(Schedule schedule) {
        if (schedule.getId() == null) {
            schedule.setId(1l);
        }
        return scheduleRepository.findAllByUserId(schedule.getUser().getId());
    }

    @Override
    public MailMsg queryDeadLineSchs() {
        LocalDateTime now = LocalDateTime.now();
        List<ScheduleRecord> overTimes = scheduleRecordRepository
                .queryAllByDeadLineBeforeAndStateEquals(now, Constants.TODO);
        List<ScheduleRecord> nearEnds = scheduleRecordRepository
                .queryAllByDeadLineBetweenAndStateEquals(now, now.plusMonths(10l), Constants.TODO);
        MailMsg mailMsg = new MailMsg(nearEnds,overTimes);
        return mailMsg;
    }

    @Override
    public List<ScheduleRecord> queryRecordList(QueryParam record) {
        return scheduleRecordRepository.querySchRecordList(record.getStartTime(), record.getEndTime(), record.getUserId());
    }


    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    @Override
    public void updateRecord(ScheduleVo record) {

        ScheduleRecord scheduleRecord = scheduleRecordRepository.findById(record.getRecordId()).get();
        scheduleRecord.setState(record.getState());
        scheduleRecordRepository.save(scheduleRecord);
//        scheduleRecordRepository.updateRecordState(record.getState(), record.getRecordId());
        if (record.getState() != -1) {
            appendRecord(scheduleRecord.getSchedule());
        }
    }

    @Override
    public void update(Schedule schedule) {
        schedule.setCreateTime(LocalDateTime.now());
        scheduleRepository.save(schedule);
        appendRecord(schedule);
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public void deleteScd(Long id) {
        scheduleRecordRepository.deleteById(id);
    }
}
