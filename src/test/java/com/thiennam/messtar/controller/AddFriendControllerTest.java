package com.thiennam.messtar.controller;

import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.service.FriendshipService;
import com.thiennam.messtar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AddFriendControllerTest {
    @Autowired
    UserService userService;
    @Autowired
    FriendshipService friendshipService;

    @Test
    void addFriend() {
        String loggedUser = "thiennam23";
        String userToAdd = "user";
        User user1 = userService.findByUsername(loggedUser);
        User user2 = userService.findByUsername(userToAdd);
        friendshipService.addFriend(user1, user2);
    }
}