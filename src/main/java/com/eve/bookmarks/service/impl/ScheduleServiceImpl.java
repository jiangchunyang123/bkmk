package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.ScheduleRecordRepository;
import com.eve.bookmarks.dao.ScheduleRepository;
import com.eve.bookmarks.dao.UserRepository;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.entitys.vo.QueryParam;
import com.eve.bookmarks.entitys.vo.ScheduleVo;
import com.eve.bookmarks.service.ScheduleService;
import com.eve.bookmarks.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
     * @param schedule
     */
    @Override
    public void appendRecord(Schedule schedule ) {
        //创建时自动创建一个代办
        ScheduleRecord record = scheduleRecordRepository.findFirstByScheduleOrderByIdDesc(schedule);
        ScheduleRecord next =schedule.builderNextRecord(record);
        scheduleRecordRepository.save(next);
    }

    @Override
    public List<Schedule> queryList(Schedule schedule) {
        if(schedule.getId()==null){
            schedule.setId(1l);
        }
      return  scheduleRepository.findAllByUserId(schedule.getUser().getId());
    }
    @Override
    public List<ScheduleRecord> queryRecordList(QueryParam record) {
        return scheduleRecordRepository.querySchRecordList(record.getStartTime(),record.getEndTime(),record.getUserId());
    }


    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    @Override
    public void updateRecord(ScheduleVo record) {
        scheduleRecordRepository.updateRecordState(record.getState(),record.getRecordId());
        ScheduleRecord scheduleRecord = scheduleRecordRepository.findById(record.getSchId()).get();
        if(record.getState()!=-1){
            appendRecord(scheduleRecord.getSchedule());
        }
    }
}
