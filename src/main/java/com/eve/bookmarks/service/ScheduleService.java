package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.Schedule;

import java.util.List;

public interface ScheduleService {
    void insert(Schedule schedule);

    List<Schedule> queryList(Schedule schedule);
    Schedule findById(Long id);

}
