package com.eve.bookmarks.controller;

import com.alibaba.fastjson.JSON;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.manager.ISessionManager;
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

    private final ISessionManager sessionManager;

    @Autowired
    public BkMarkController(BookMarkService bookMarkService, UserService userService, ISessionManager sessionManager) {
        this.bookMarkService = bookMarkService;
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    /**
     * 通过用户id获取存储的书签树信息
     *
     * @param sessionId 用户sessionId
     * @return 书签
     */
    @GetMapping(value = "/{sessionId}", consumes = "application/json")
    public ResponseEntity<BookMarkMongo> queryBookMarkById(@PathVariable String sessionId) {
        User user = sessionManager.getUser(sessionId);
        BookMarkMongo bookMarkMongo = bookMarkService.getBookMarktree(user.getMongoId());
        return ResponseEntity.ok(bookMarkMongo);
    }


    /**
     * 同步书签信息
     *
     * @param sessionId 用户id
     * @param bookmarks 本地书签
     * @return 调整命令
     */
    @PostMapping(value = "/{sessionId}")
    public Result synBookMarks(@PathVariable(value="userId") String sessionId, @RequestBody BookMarkMongo bookmarks) {
        User user = sessionManager.getUser(sessionId);
        Map<String, Object> map = bookMarkService.syncBookMark(bookmarks.getValue(), user);
        logger.debug("results:", JSON.toJSONString(map));
        return new Result(Constants.STATUS_SUCCESS, map);
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello world！";
    }
}
