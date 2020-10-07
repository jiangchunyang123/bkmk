package com.eve.bookmarks.manager;

import com.alibaba.fastjson.JSON;
import com.eve.bookmarks.sys.domain.User;
import com.eve.bookmarks.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager implements ISessionManager {

    Logger logger = LoggerFactory.getLogger(SessionManager.class);

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    Map<String, User> sessions = new ConcurrentHashMap<>();

    @Override
    public String login(User user) {
        User dbUser = userService.getByUserName(user.getUserName());
        if (dbUser == null) {
            throw new RuntimeException("user does not exists!!");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("userName or password no correct!!");
        }
//        String sessionId = redisTemplate.opsForValue().get(dbUser.get)
        for (String key : sessions.keySet()) {
            if (sessions.get(key).getUserName().equals(user.getUserName())) {
                return key;
            }
        }

        String sessionId = UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(sessionId, dbUser);
        sessions.put(sessionId, user);
        logger.info("put a user :"+ JSON.toJSONString(user));
        return sessionId;
    }

    @Override
    public User getUser(String sessionId) {
        logger.info("sessions users :"+ JSON.toJSONString(sessions));
        User user = sessions.get(sessionId);
        logger.info("got user:"+user.getUserName());
        return user;
    }
}
