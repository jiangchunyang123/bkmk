package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.ScheduleMapper;
import com.eve.bookmarks.dao.ScheduleRecordMapper;
import com.eve.bookmarks.dao.UserMapper;
import com.eve.bookmarks.entitys.po.Schedule;
import com.eve.bookmarks.entitys.po.ScheduleRecord;
import com.eve.bookmarks.entitys.vo.MailMsg;
import com.eve.bookmarks.entitys.vo.QueryParam;
import com.eve.bookmarks.entitys.vo.ScheduleVo;
import com.eve.bookmarks.service.ScheduleService;
import com.eve.bookmarks.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ScheduleRecordMapper scheduleRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(Schedule schedule) {
        schedule.setCreateTime(LocalDateTime.now());
        scheduleMapper.insert(schedule);
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
        ScheduleRecord record = scheduleRecordMapper.findFirstByScheduleOrderByIdDesc(schedule);
        ScheduleRecord next = schedule.builderNextRecord(record);
        scheduleRecordMapper.insert(next);
    }

    @Override
    public List<Schedule> queryList(Schedule schedule) {
        if (schedule.getId() == null) {
            schedule.setId(1l);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userName",schedule.getUserName());
        return scheduleMapper.selectByMap(map);
    }

    @Override
    public MailMsg queryDeadLineSchs() {
        LocalDateTime now = LocalDateTime.now();
        List<ScheduleRecord> overTimes = scheduleRecordMapper
                .queryAllByDeadLineBeforeAndStateEquals(now, Constants.TODO);
        List<ScheduleRecord> nearEnds = scheduleRecordMapper
                .queryAllByDeadLineBetweenAndStateEquals(now, now.plusMonths(10l), Constants.TODO);
        MailMsg mailMsg = new MailMsg(nearEnds,overTimes);
        return mailMsg;
    }

    @Override
    public List<ScheduleRecord> queryRecordList(QueryParam record) {
        return scheduleRecordMapper.querySchRecordList(record.getStartTime(), record.getEndTime(),
                record.getUserId());
    }


    @Override
    public Schedule findById(Long id) {
        return scheduleMapper.selectById(id);
    }

    @Override
    public void updateRecord(ScheduleVo record) {

        ScheduleRecord scheduleRecord = scheduleRecordMapper.selectById(record.getRecordId());
        scheduleRecord.setState(record.getState());
        scheduleRecordMapper.insert(scheduleRecord);
        Schedule schedule = scheduleMapper.selectById(record.getSchId());
//        scheduleRecordMapper.updateRecordState(record.getState(), record.getRecordId());
        if (record.getState() != -1) {
            appendRecord(schedule);
        }
    }

    @Override
    public void update(Schedule schedule) {
        schedule.setCreateTime(LocalDateTime.now());
        scheduleMapper.insert(schedule);
        appendRecord(schedule);
    }

    @Override
    public void delete(Long id) {
        scheduleMapper.deleteById(id);
    }

    @Override
    public void deleteScd(Long id) {
        scheduleRecordMapper.deleteById(id);
    }
}
