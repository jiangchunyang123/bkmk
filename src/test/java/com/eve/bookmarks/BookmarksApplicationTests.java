package com.eve.bookmarks;

import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarksApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void createUser() {
        User user = new User();
        user.setUserName("god");
        user.setPassword("666");
        user.setState(1);
        user.setVersion(0l);
        userService.createUser(user);
        System.out.printf("user:"+JSONObject.toJSONString(user));
    }

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("777", "456");
        String val = redisTemplate.opsForValue().get("777").toString();
        assert val!=null;
    }
}
