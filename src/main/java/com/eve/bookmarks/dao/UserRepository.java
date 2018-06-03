package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getByUid(String uid);
}
