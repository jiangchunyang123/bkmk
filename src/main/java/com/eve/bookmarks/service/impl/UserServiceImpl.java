package com.eve.bookmarks.service.impl;

import com.eve.bookmarks.dao.UserRepository;
import com.eve.bookmarks.entitys.BookMarkMongo;
import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import com.eve.bookmarks.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public int createUser(User user){

        //入库
        userRepository.save(user);

        //生成MongoDBid
        BookMarkMongo bookMarkMongo = new BookMarkMongo("");
        bookMarkMongo.setUid(user.getId());
        bookMarkMongo.setVersion(user.getVersion());
        mongoTemplate.save(bookMarkMongo,Constants.BOOK_MARK_MONGODB_NAME);

        user.setMongoId(bookMarkMongo.getId());
        userRepository.save(user);

        return 1;
    }

    @Override
    public User get(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }
}
