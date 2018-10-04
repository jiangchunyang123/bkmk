package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sch")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @RequestMapping(method = RequestMethod.POST)
    public Result create(Schedule schedule) {
        scheduleService.insert(schedule);
        return new Result(1, "success");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result delete(@RequestParam("id") String uid, HttpServletRequest httpServletRequest) {
        return new Result(1, "success");
    }

    @PutMapping
    public Result update(@RequestParam("schedule") Schedule schedule, HttpServletRequest httpServletRequest) {
        scheduleService.insert(schedule);
        return new Result(1, "success");
    }
    @PutMapping(value="/rcd")
    public Result updateRcd(ScheduleRecord record, HttpServletRequest httpServletRequest) {
        scheduleService.updateRecord(record);
        return new Result(1, "success");
    }
    @GetMapping(value = "/{uid}")
    public Result get(@PathVariable("uid") Long id) {
        Schedule schedule = scheduleService.findById(id);
        return new Result(1, "success", schedule);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result queryUsers(Schedule schedule, HttpServletRequest httpServletRequest) {
        List<Schedule> scheduleList = scheduleService.queryList(schedule);
        return new Result(1, "success", scheduleList);
    }
    @RequestMapping(value="/rcd",method = RequestMethod.GET)
    public Result querySchRecord(ScheduleRecord schedule, HttpServletRequest httpServletRequest) {
        Object val = redisTemplate.opsForValue().get(schedule.getUser().getUid());
        if(val==null){
            return new Result(0,"sessionout");
        }
        List<ScheduleRecord> scheduleList = scheduleService.queryRecordList(schedule);
        return Result.Success(scheduleList);
    }

    @RequestMapping("/p")
    public ModelAndView index() {
        return new ModelAndView("schList");
    }
}
