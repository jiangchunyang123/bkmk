package com.eve.bookmarks.manager;

import com.eve.bookmarks.sys.domain.User;

public interface ISessionManager {
    String login(User user);

    User getUser(String sessionId);
}
