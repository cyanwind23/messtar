package com.thiennam.messtar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.AddFriendRequestDto;
import com.thiennam.messtar.service.FriendshipService;
import com.thiennam.messtar.service.UserService;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addfriend")
public class AddFriendController {

    @Autowired
    UserService userService;

    @Autowired
    FriendshipService friendshipService;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @GetMapping
    public String showAddFriendPage() {
        return "addfriend";
    }

    @PostMapping
    @ResponseBody
    public String addFriend(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        AddFriendRequestDto requestDto = gson.fromJson(request, AddFriendRequestDto.class);

        String loggedUser = requestDto.getLoggedUser();
        String userToAdd = requestDto.getUserToAdd();

        // check if they add friend to themselves
        if (loggedUser.equals(userToAdd)) {
            return "{\"data\" : \"Failed\"}";
        }

        User user1 = userService.findByUsername(loggedUser);
        User user2 = userService.findByUsername(userToAdd);
        if (user1 == null || user2 == null) {
            return "{\"data\" : \"Failed\"}";
        } else {
            friendshipService.addFriend(user1, user2);
//            Logger logger = LoggerFactory.getLogger(AddFriendController.class);
//            logger.info(loggedUser + " " + userToAdd);
        }
        return "{\"data\" : \"send OK\"}";
    }
}
