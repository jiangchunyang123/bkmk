package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.User;

public interface UserService {
    int createUser(User user);

    User get(int id);

    User getByUserName(String userName);
}
