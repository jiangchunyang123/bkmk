package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.po.User;
import com.eve.bookmarks.manager.ISessionManager;
import com.eve.bookmarks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    @ResponseBody
    public String login(User user) {
        return sessionManager.login(user);
    }
}
