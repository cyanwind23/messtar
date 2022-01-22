package com.thiennam.messtar;

import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testUserRepo() {
        User user1 = userService.findByUsername("user");
    }
}
