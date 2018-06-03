package com.eve.bookmarks.controller;

import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.BookMarkService;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.utils.JSONUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookmk")
public class BkMarkController {

    private Logger logger = LoggerFactory.getLogger(BkMarkController.class);

    @Autowired
    private BookMarkService bookMarkService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/get/{uid}", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<BookMarkMongo> queryBookMarkById(@PathVariable String uid) {
        User user = userService.findByUid(uid);
        BookMarkMongo bookMarkMongo =bookMarkService.getBookMarktree(user.getBookmarkMongoId());
        return ResponseEntity.ok(bookMarkMongo);
    }

    @RequestMapping(value = "/put/{uid}", method = RequestMethod.PUT)
    public Result synBookMarks(@PathVariable String uid, @RequestParam("bookmarks") String bookmarks, HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = JSONObject.parseObject(bookmarks);
        bookMarkService.saveBookMarks(jsonObject.get("data"),"jiangchunyang");
        return new Result(0, "success");
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello worldsdd";
    }
}
