package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    User getByUid(String uid);

    /**
     * 更新书签，提升版本号
     * @param uid
     * @param mongoId
     * @return
     */
    @Modifying
    @Query("update User u set u.version = u.version+1,u.mongoId=:mongoId  where u.uid = :uid")
    int addVersion(@Param("uid")Long uid, @Param("mongoId") String mongoId);
}
