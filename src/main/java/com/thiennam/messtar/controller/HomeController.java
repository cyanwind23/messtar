package com.thiennam.messtar.controller;

import com.google.gson.GsonBuilder;
import com.thiennam.messtar.entity.Friendship;
import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomTypeEnum;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.entity.dto.UserDto;
import com.thiennam.messtar.service.FriendshipService;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import com.thiennam.messtar.util.MessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    FriendshipService friendshipService;
    @Autowired
    private MessManager messManager;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        User user = userService.toUser(userDto);
        userService.addNewUser(user);
        return "redirect:/login";
    }

    @GetMapping("/test")
    public String test() {
        return "room/test";
    }

    @GetMapping("/fetchallroom/{loggedUser}")
    @ResponseBody
    public String fetchAllRoom(@PathVariable(name = "loggedUser") String loggedUser) {
        User user = userService.findByUsername(loggedUser);
        List<Room> rooms = roomService.findByUser(user);
        List<UUID> roomIds = new ArrayList<>();
        for (Room room : rooms) {
            roomIds.add(room.getId());
        }
        return new GsonBuilder().create().toJson(roomIds);
    }
    @PutMapping("/online")
    @ResponseBody
    public String notifyOnline() {
        User user = getLoggedUser();
        user.setOnline(true);

        MessageDto messageDto = buildOnlineNotification(user.getUsername());
        // notify to all friends
        List<Friendship> friends = friendshipService.findAllFriend(user);
        for (Friendship friend : friends) {
            String toFriend = friend.getUser2().getUsername();
            messageDto.setToUser(toFriend);
            messManager.sendToUser(toFriend, messageDto);
        }
        // clear messageDto toUser for Room
        messageDto.setToUser(null);

        // notify to all multiple room
        List<Room> rooms = roomService.findByUserAndType(user, RoomTypeEnum.MULTIPLE);
        for (Room room : rooms) {
            messageDto.setToRoomId(room.getId().toString());
            messManager.sendToRoom(room.getId(), messageDto);
        }
        userService.save(user);
        return "{\"data\" : \"OK\"}";
    }

    private MessageDto buildOnlineNotification(String sender) {
        MessageDto mess = new MessageDto();
        mess.setSender(sender);
        mess.setType("NOTIFICATION");
        mess.setContent("online");
        return mess;
    }
    // TODO: This method is used in many places, should be change for suitable
    private User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUser = auth.getName();
        return userService.findByUsername(loggedUser);
    }
}
