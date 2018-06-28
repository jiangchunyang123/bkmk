package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.Schedule;
import com.eve.bookmarks.entitys.ScheduleRecord;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestParam("schedule") Schedule schedule, HttpServletRequest httpServletRequest) {
        scheduleService.insert(schedule);
        return new Result(1, "success");
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public Result get(@RequestParam("id") Long id, HttpServletRequest httpServletRequest) {
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
        User user = schedule.getUser();
        Object val = redisTemplate.opsForValue().get(schedule.getUser().getUname());
        if(val==null){
            return new Result(0,"sessionout");
        }
        List<ScheduleRecord> scheduleList = scheduleService.queryRecordList(schedule);
        return Result.Success();
    }
    @RequestMapping("/p")
    public ModelAndView index() {
        return new ModelAndView("schList");
    }
}
