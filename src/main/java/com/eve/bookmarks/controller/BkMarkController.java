package com.eve.bookmarks.controller;

import com.alibaba.fastjson.JSON;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.BookMarkService;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bookmk")
public class BkMarkController {

    private Logger logger = LoggerFactory.getLogger(BkMarkController.class);

    private final BookMarkService bookMarkService;

    private final UserService userService;

    @Autowired
    public BkMarkController(BookMarkService bookMarkService, UserService userService) {
        this.bookMarkService = bookMarkService;
        this.userService = userService;
    }

    /**
     * 通过用户id获取存储的书签树信息
     *
     * @param uid 用户id
     * @return 书签
     */
    @GetMapping(value = "/{uid}", consumes = "application/json")
    public ResponseEntity<BookMarkMongo> queryBookMarkById(@PathVariable int uid) {
        User user = userService.get(uid);
        BookMarkMongo bookMarkMongo = bookMarkService.getBookMarktree(user.getMongoId());
        return ResponseEntity.ok(bookMarkMongo);
    }


    /**
     * 同步书签信息
     *
     * @param userId 用户id
     * @param bookmarks 本地书签
     * @return 调整命令
     */
    @PostMapping(value = "/{userId}")
    public Result synBookMarks(@PathVariable(value="userId") int userId, @RequestBody BookMarkMongo bookmarks) {
        User user = userService.get(userId);
        Map<String, Object> map = bookMarkService.syncBookMark(bookmarks.getValue(), user);
        logger.debug("results:", JSON.toJSONString(map));
        return new Result(Constants.STATUS_SUCCESS, map);
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello world！";
    }
}
