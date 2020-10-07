package com.eve.bookmarks.sys.service.impl;

import com.eve.bookmarks.bookmark.domain.BookMarkMongo;
import com.eve.bookmarks.common.utils.Constants;
import com.eve.bookmarks.sys.dao.UserMapper;
import com.eve.bookmarks.sys.domain.User;
import com.eve.bookmarks.sys.domain.UserExample;
import com.eve.bookmarks.sys.service.UserService;
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
        bookMarkMongo.setUserName(user.getUserName());
        bookMarkMongo.setVersion(user.getVersion());
        mongoTemplate.save(bookMarkMongo, Constants.BOOK_MARK_MONGODB_NAME);

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
