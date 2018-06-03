package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/u")
public class UserController {
    @Autowired
    private UserService userService;

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
}
