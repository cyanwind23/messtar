package com.thiennam.messtar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.service.MessageService;
import com.thiennam.messtar.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/send")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    RoomService roomService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    Gson gson;

    @PostMapping("/user")
    @ResponseBody
    public String sendMessageNotificationToUser(@RequestBody String requestBody) {
        MessageDto messageDto = parsePayload(requestBody);
//        User sender = userService.findByUsername(messageDTO.getSender());
//        String notification = String.format(
//                "{\"roomId\" : \"%s\", \"roomName\" : \"%s\", \"content\" : \"%s\", \"type\" : \"%s\"}",
//                messageDTO.getroomId(),
//                sender.getFirstname() + " " + sender.getLastname(),
//                messageDTO.getContent(),
//                "NEW_MESSAGE"
//        );
//        System.out.println("Send to notifi: " + ApplicationConfig.USER_SOCKET_DEST_FREFIX + username);
//        simpMessagingTemplate.convertAndSend(ApplicationConfig.USER_SOCKET_DEST_FREFIX + username, notification);
        simpMessagingTemplate.convertAndSendToUser(messageDto.getToUsername(), "/queue/message", messageDto);
        return "{\"data\" : \"send OK\"}";
    }

    @PostMapping("/room")
    @ResponseBody
    public String sendMessageToRoom(@RequestBody String requestBody) {
        MessageDto messageDto = parsePayload(requestBody);

        Message message = messageService.toMessage(messageDto);
//        messageService.save(message);

        UUID toRoomId = UUID.fromString(messageDto.getToRoomId());
//        Room room = roomService.findById(toRoomId);
//        room.setLastChange(LocalDateTime.now());
//        roomService.save(room);
        simpMessagingTemplate.convertAndSend("/room/" + toRoomId, messageDto);
        return "{\"data\" : \"OK\"}";
    }

    private MessageDto parsePayload(String payload) {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson.fromJson(payload, MessageDto.class);
    }
}
