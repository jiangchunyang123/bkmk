package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/u")
public class UserController {
    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @RequestMapping(method = RequestMethod.POST)
    public Result createUser(@RequestParam("user") User user, HttpServletRequest httpServletRequest) {
        userService.createUser(user);
        return new Result(0, "success");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result delete(@RequestParam("uid") String uid, HttpServletRequest httpServletRequest) {
        //userService.createUser(user);
        return new Result(0, "success");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestParam("user") User user, HttpServletRequest httpServletRequest) {
        userService.createUser(user);
        return new Result(0, "success");
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public Result get(@RequestParam("user") User user, HttpServletRequest httpServletRequest) {
        // userService.createUser(user);
        return new Result(0, "success");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result queryUsers(User user, HttpServletRequest httpServletRequest) {
        // userService.createUser(user);
        return new Result(0, "success");
    }
    @RequestMapping("/hello")
    public Result hello(User user, HttpServletRequest httpServletRequest) {
        String uid = user.getUid();
        String[] ids = uid.split(",");
        redisTemplate.opsForValue().set(ids[0],"true",10,TimeUnit.HOURS);
        return new Result(1, "helloha");
    }
}
