package com.eve.bookmarks.sys.controller;

import com.eve.bookmarks.common.domain.Result;
import com.eve.bookmarks.manager.ISessionManager;
import com.eve.bookmarks.sys.domain.User;
import com.eve.bookmarks.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class GlobalController {
    final UserService userService;

    final ISessionManager sessionManager;

    @Autowired
    public GlobalController(UserService userService, ISessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    //    @RequestMapping("/bookList")
//    public String bookList() {
//        return "bookList";
//    }


    @PostMapping("/login")
    @ResponseBody
    public Result login(User user) {
        return Result.Success(sessionManager.login(user));
    }
}
