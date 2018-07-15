package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.ScheduleRecordRepository;
import com.eve.bookmarks.dao.ScheduleRepository;
import com.eve.bookmarks.dao.UserRepository;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.ScheduleService;
import com.eve.bookmarks.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
@Autowired
private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleRecordRepository scheduleRecordRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void insert(Schedule schedule) {
        schedule.setCreateTimeMils(DateUtils.nowMils());
        scheduleRepository.save(schedule);

        appendRecord(schedule);
    }

    /**
     * 增加record
     * @param schedule
     */
    @Override
    public void appendRecord(Schedule schedule) {
        //创建时自动创建一个代办
        ScheduleRecord record = scheduleRecordRepository.findFirstByScheduleOrderByIdDesc(schedule);
        ScheduleRecord next =schedule.builderNextRecord(record);
        scheduleRecordRepository.save(next);
    }

    @Override
    public List<Schedule> queryList(Schedule schedule) {
      return  scheduleRepository.findAllByUserId(schedule.getUser().getId());
    }
    @Override
    public List<ScheduleRecord> queryRecordList(ScheduleRecord record) {
        User user = userRepository.getByUid(record.getUser().getUid());

        Sort sort = null;
        if ("Desc".equals(record.direction)) {
            sort = new Sort(Sort.Direction.DESC, record.descName);
        } else {
            sort = new Sort(Sort.Direction.ASC, record.descName);
        }
        Pageable pageable = PageRequest.of(record.pageIndex, record.pageSize, sort);

        return scheduleRecordRepository.findAllByUser(user,pageable);
    }
    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    @Override
    public void updateRecord(ScheduleRecord record) {
        scheduleRecordRepository.save(record);
    }
}
