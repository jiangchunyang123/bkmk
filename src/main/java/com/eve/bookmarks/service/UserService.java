package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.po.User;

public interface UserService {
    int createUser(User user);

    User get(Long id);

    User getByUserName(String userName);
}
