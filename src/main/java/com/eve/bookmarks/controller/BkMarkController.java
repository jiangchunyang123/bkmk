package com.eve.bookmarks.controller;

import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.BookMarkService;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.utils.Constants;
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

    /**
     * 通过用户id获取存储的书签树信息
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "/{uid}", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<BookMarkMongo> queryBookMarkById(@PathVariable String uid) {
        User user = userService.findByUid(uid);
        BookMarkMongo bookMarkMongo = bookMarkService.getBookMarktree(user.getMongoId());
        return ResponseEntity.ok(bookMarkMongo);
    }


    /**
     * 同步书签信息
     *
     * @param userId
     * @param bookmarks
     * @return
     */
    @RequestMapping(value = "/{uid}", method = RequestMethod.POST)
    public Result synBookMarks(@PathVariable String userId, @RequestParam("bookmarks") String bookmarks) {

        User user = userService.get(userId);
        Map<String, Object> map = bookMarkService.syncBookMark(bookmarks, user);

        return new Result(Constants.STATUS_SUCCESS, map);
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello world！";
    }
}
