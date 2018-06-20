package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByUserId(Long userId);
}
