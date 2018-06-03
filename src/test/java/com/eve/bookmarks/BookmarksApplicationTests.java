package com.eve.bookmarks;

import com.eve.bookmarks.entitys.User;
import com.eve.bookmarks.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarksApplicationTests {
@Autowired
    UserService userService;
	@Test
	public void contextLoads() {
	    User user = new User();
	    user.setUid("jiangchunyang");
	    user.setUname("蒋春阳");
	    user.setPassword("654321");
	    user.setState(1);
        userService.createUser(user);
	}

}
