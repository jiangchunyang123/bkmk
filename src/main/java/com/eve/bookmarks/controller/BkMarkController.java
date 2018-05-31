package com.eve.bookmarks.controller;

import com.eve.bookmarks.entitys.BookMark;
import com.eve.bookmarks.entitys.Result;
import com.eve.bookmarks.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bookmk")
public class BkMarkController {
    private Logger logger = LoggerFactory.getLogger(BkMarkController.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/get/{uid}",method = RequestMethod.GET,consumes="application/json")
    public Result queryBookMarkById(@PathVariable String  uid){
        BookMark bokmark = mongoTemplate.findById(uid, BookMark.class);
        return new Result(0,"success",JSONUtils.objectToJsonStr(bokmark));
    }
    @RequestMapping(value="/put/{uid}",method = RequestMethod.PUT)
    public Result synBookMarks(@PathVariable String  uid,@RequestParam("bookmarks") String bookmarks){
        Map m = JSONUtils.StringToJsonObj(bookmarks);
        System.out.println("bookmarks:"+bookmarks);
        System.out.println("m:"+m);

        return new Result(0,"success");
    }
    @RequestMapping(value="/hello")
    public String hello(){
        return "hello worldsdd";
    }
}
