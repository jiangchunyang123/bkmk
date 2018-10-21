package com.eve.bookmarks.manager;

import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionManager implements ISessionManager{
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Override
    public String login(User user) {
        User dbUser = userService.getByUserName(user.getUserName());
        if(dbUser==null){
            throw new RuntimeException("user does not exists!!");
        }
        if(!dbUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException("userName or password no correct!!");
        }
        String sessionId = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(user.getUserName(), sessionId);
        return sessionId;
    }
}
