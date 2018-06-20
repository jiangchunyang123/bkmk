package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.ScheduleRepository;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
@Autowired
private ScheduleRepository scheduleRepository;
    @Override
    public void insert(Schedule schedule) {
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
