package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.manager.ISessionManager;
import com.eve.bookmarks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String login(User user) {
        return sessionManager.login(user);
    }
}
