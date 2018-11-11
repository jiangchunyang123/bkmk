package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.UserMapper;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.po.User;
import com.eve.bookmarks.entitys.po.UserExample;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public int createUser(User user){

        //入库
        userMapper.insert(user);

        //生成MongoDBid
        BookMarkMongo bookMarkMongo = new BookMarkMongo("");
        bookMarkMongo.setUid(user.getId());
        bookMarkMongo.setVersion(user.getVersion());
        mongoTemplate.save(bookMarkMongo,Constants.BOOK_MARK_MONGODB_NAME);

        user.setMongoId(bookMarkMongo.getId());
        userMapper.insertSelective(user);

        return 1;
    }

    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getByUserName(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        List<User> result = userMapper.selectByExample(userExample);
        return result==null?null:result.get(0);
    }
}
