package com.eve.bookmarks.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        System.out.println("This is exception handler method!");
        return null;
    }
}