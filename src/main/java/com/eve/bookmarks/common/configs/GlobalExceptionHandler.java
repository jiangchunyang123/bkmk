package com.eve.bookmarks.common.configs;


import com.eve.bookmarks.common.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler   {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public Result resolveException(Exception ex) {
        logger.warn("捕获到全局异常=",ex);
        return Result.failed(ex.getMessage());
    }
}