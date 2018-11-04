package com.eve.bookmarks.manager;

import com.eve.bookmarks.entitys.User;

public interface ISessionManager {
    String login(User user);

    User getUser(String sessionId);
}
