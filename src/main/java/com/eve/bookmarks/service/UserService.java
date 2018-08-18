package com.eve.bookmarks.service;

import com.eve.bookmarks.entitys.User;

public interface UserService {
    int createUser(User user);

    User findByUid(String uid);

    User get(String id);
}
