package com.eve.bookmarks;

import com.alibaba.fastjson.JSONObject;
import com.eve.bookmarks.entitys.po.Schedule;
import com.eve.bookmarks.entitys.po.User;
import com.eve.bookmarks.service.ScheduleService;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.task.ScheduleTaskConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarksApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ScheduleTaskConfig scheduleTaskConfig;

    @Autowired
    private ScheduleService scheduleService;

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

    /**
     * 测试邮件发送系统，是否能按照指定规则发送
     */
    @Test
    public void mailTest(){
        initMailMsg();
        scheduleTaskConfig.senMail();
        destroyMailMsg();
    }

    private void destroyMailMsg() {

    }

    private void initMailMsg() {
        Schedule nearEndsMail = Schedule.newDefault();
        nearEndsMail.setTitle("test mail send");
        nearEndsMail.setFirstDeadLine(LocalDateTime.now().plusMinutes(5));
        nearEndsMail.setUserName("god");

        scheduleService.insert(nearEndsMail);

        scheduleService.appendRecord(nearEndsMail);

        Schedule overTimeMail = new Schedule();
        overTimeMail.setTitle("test mail send overtime");
        overTimeMail.setFirstDeadLine(LocalDateTime.now().plusMinutes(-5));
        nearEndsMail.setUserName("god");
        scheduleService.insert(overTimeMail);
        scheduleService.appendRecord(overTimeMail);

    }

}
