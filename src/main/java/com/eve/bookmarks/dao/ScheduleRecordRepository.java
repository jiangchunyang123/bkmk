package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRecordRepository extends CrudRepository<ScheduleRecord, Long> {
    List<ScheduleRecord> findAllByUser(User user,Pageable pageable);

    ScheduleRecord findFirstByScheduleOrderByIdDesc(Schedule schedule);
    @Modifying
    @Query("update ScheduleRecord s set s.state =:state where s.id =:id")
    Integer updateRecordState(@Param(value = "state") int state,@Param(value = "id")Long id);
}
