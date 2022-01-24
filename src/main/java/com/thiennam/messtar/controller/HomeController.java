package com.thiennam.messtar.controller;

import com.google.gson.GsonBuilder;
import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.UserDto;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/room")
    public String room() {
        return "single-room";
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

}
