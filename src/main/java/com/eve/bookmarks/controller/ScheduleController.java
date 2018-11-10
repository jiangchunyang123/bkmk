package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.entitys.vo.QueryParam;
import com.eve.bookmarks.entitys.vo.ScheduleVo;
import com.eve.bookmarks.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/sch")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping
    public Result create(Schedule schedule) {
        scheduleService.insert(schedule);
        return Result.Success();
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") String uid) {
        return new Result(1, "success");
    }

    @PutMapping("/{id}")
    public Result update(@RequestParam("schedule") Schedule schedule) {
        scheduleService.update(schedule);
        return Result.Success();
    }

    @PostMapping(value = "/rcd",consumes = "application/json")
    public Result updateRcd(@RequestBody ScheduleVo record) {
        scheduleService.updateRecord(record);
        return Result.Success();
    }

    @GetMapping(value = "/{uid}")
    public Result get(@PathVariable("uid") Long id) {
        Schedule schedule = scheduleService.findById(id);
        return Result.Success(schedule);
    }

    @GetMapping
    public Result query(Schedule schedule) {
        List<Schedule> scheduleList = scheduleService.queryList(schedule);
        return Result.Success(scheduleList);
    }

    @GetMapping(value = "/rcd")
    public Result querySchRecord(QueryParam param) {
//        Object val = redisTemplate.opsForValue().get(schedule.getUser().getId());
//        if(val==null){
//            return new Result(0,"sessionout");
//        }
        List<ScheduleRecord> scheduleList = scheduleService.queryRecordList(param);
        return Result.Success(scheduleList);
    }

    @RequestMapping("/p")
    public ModelAndView index() {
        return new ModelAndView("schList");
    }
}
