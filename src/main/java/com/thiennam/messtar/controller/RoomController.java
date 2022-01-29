package com.thiennam.messtar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomTypeEnum;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.entity.dto.RoomContext;
import com.thiennam.messtar.entity.dto.RoomDto;
import com.thiennam.messtar.service.MessageService;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    // TODO: will be redirect to other path
    @GetMapping("")
    public String redirect() {
        return "redirect:/room/single";
    }

    @GetMapping("/messtar")
    public String messtar() {
        return "room/messtar";
    }

    @GetMapping("/single")
    public String getSingleRoomMainPage(Model model) {
        User user = getLoggedUser();
        List<Room> rooms = roomService.findByUserAndTypeSorted(user, RoomTypeEnum.SINGLE);

        List<RoomDto> roomDtos = roomService.prepareSingleRoomDtos(rooms, user);

        model.addAttribute("rooms", roomDtos);
        return "room/single";
    }

    @GetMapping("/single/{roomId}")
    public String displayRoom(@PathVariable(name = "roomId") String roomId, Model model) {
        User user = getLoggedUser();
        boolean validRoom = false;
        List<Room> rooms = roomService.findByUserAndTypeSorted(user, RoomTypeEnum.SINGLE);
        // check valid user in this room
        for (Room room : rooms) {
            if (room.getId().toString().equals(roomId)) {
                validRoom = true;
            }
        }
        if (!validRoom) {
            return "error/403";
        }
        List<RoomDto> roomDtos = roomService.prepareSingleRoomDtos(rooms, user);
        // get `thisRoomDto`
        RoomDto thisRoom = null;
        for (RoomDto roomDto : roomDtos) {
            if (roomDto.getRoomId().equals(roomId)) {
                thisRoom = roomDto;
            }
        }
        model.addAttribute("rooms", roomDtos);
        model.addAttribute("thisRoom", thisRoom);
        return "room/single-room";
    }

    private User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUser = auth.getName();
        return userService.findByUsername(loggedUser);
    }

    @GetMapping("/context/{roomId}")
    @ResponseBody
    public String getRoomContext(@PathVariable(name = "roomId") String roomId) {
        boolean invalidRequest = true;
        User loggedUser = getLoggedUser();

        Room room = roomService.findById(UUID.fromString(roomId));
        RoomDto roomDto = roomService.toDto(room);
        for (String username : roomDto.getRoomUsers()) {
            if (username.equals(loggedUser.getUsername())) {
                invalidRequest = false;
                break;
            }
        }
        if (!invalidRequest) { // continue
            RoomContext roomContext = new RoomContext();
            roomContext.setRoom(roomDto);
            roomContext.setLoggedUser(loggedUser.getUsername());

            List<Message> messages = messageService.find300LatestByRoom(room);
            List<MessageDto> messageDtos = messageService.toMessageDto(messages, loggedUser);
            roomContext.setMessages(messageDtos);

            Gson gson = new GsonBuilder().create();
            return gson.toJson(roomContext);
        }
        return "";
    }

    @GetMapping("/multiple/rooms")
    @ResponseBody
    public String getMultipleRoom() {
        User user = getLoggedUser();
        List<Room> rooms = roomService.findByUserAndTypeSorted(user, RoomTypeEnum.MULTIPLE);
        List<RoomDto> roomDtos = roomService.toDto(rooms);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(roomDtos);
    }

}
