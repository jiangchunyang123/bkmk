package com.eve.bookmarks;

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
    public void contextLoads() {
        User user = new User();
        user.setUid("jcy");
        user.setUname("chunyang");
        user.setPassword("666");
        user.setState(1);
        userService.createUser(user);
    }

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("l", "456");
        System.out.printf( redisTemplate.opsForValue().get("l").toString());
    }
}
