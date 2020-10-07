package com.eve.bookmarks.sys.service;

import com.eve.bookmarks.sys.domain.User;

public interface UserService {
    int createUser(User user);

    User get(Long id);

    User getByUserName(String userName);
}
