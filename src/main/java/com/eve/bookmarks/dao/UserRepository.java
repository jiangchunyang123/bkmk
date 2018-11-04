package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    User getById(Long uid);

    User getByUserName(String userName);
}
