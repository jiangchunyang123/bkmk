package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long>, JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserId(Long userId);
}
