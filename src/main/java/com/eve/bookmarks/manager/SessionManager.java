package com.eve.bookmarks.manager;

import com.alibaba.fastjson.JSON;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager implements ISessionManager {
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
        return sessionId;
    }

    @Override
    public User getUser(String sessionId) {
        return sessions.get(sessionId);
//        return (User) redisTemplate.opsForValue().get(sessionId);
    }
}
