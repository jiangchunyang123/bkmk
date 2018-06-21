package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.ScheduleRepository;
import com.eve.bookmarks.entitys.Schedule;
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
    @Override
    public void insert(Schedule schedule) {
        //创建时间
        Long milliSecond = DateUtils.getTimeMils(LocalDateTime.now());
        schedule.setCreateTimeMils(milliSecond.toString());
        //首次截止日期转换
        LocalDateTime firstDeadLine = DateUtils.strToDate(schedule.getFirstDeadLine(),"yyyy-MM-dd HH:mm");
        Long firstDeadL = DateUtils.getTimeMils(firstDeadLine);
        schedule.setFirstDeadLine(firstDeadL.toString());
        //用户id
        schedule.setUserId(193l);
        scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> queryList(Schedule schedule) {
      return  scheduleRepository.findAllByUserId(schedule.getUserId());
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).get();
    }
}
