package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/u")
public class UserController {
    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @PostMapping()
    public Result createUser(@RequestBody User user, HttpServletRequest httpServletRequest) {
        userService.createUser(user);
        return new Result(0, "success");
    }

    @DeleteMapping("/{uid}")
    public Result delete(@PathVariable("uid") String uid, HttpServletRequest httpServletRequest) {
        return new Result(0, "success");
    }

    @PutMapping
    public Result update(@RequestParam("user") User user, HttpServletRequest httpServletRequest) {
        userService.createUser(user);
        return new Result(0, "success");
    }

    @GetMapping(value = "/{uid}")
    public Result get(@RequestParam("uid") String uid, HttpServletRequest httpServletRequest) {
        return new Result(0, "success");
    }

    @GetMapping("/list")
    public Result queryUsers(User user, HttpServletRequest httpServletRequest) {
        return new Result(0, "success");
    }

    @RequestMapping("/hello/{uid}")
    public Result hello(@PathVariable String uid, HttpServletRequest httpServletRequest) {
        redisTemplate.opsForValue().set(uid,"true",10,TimeUnit.HOURS);
        return new Result(1, "helloha");
    }
}
