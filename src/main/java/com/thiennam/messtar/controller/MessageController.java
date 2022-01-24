package com.thiennam.messtar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thiennam.messtar.entity.Message;
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
    public String sendMessageToUser(@RequestBody String requestBody) {
        MessageDto messageDto = parsePayload(requestBody);
//        messageService.saveFromDto(messageDto);
        simpMessagingTemplate.convertAndSendToUser(messageDto.getToUser(), "/queue/message", messageDto);
        return "{\"data\" : \"send OK\"}";
    }

    @PostMapping("/room")
    @ResponseBody
    public String sendMessageToRoom(@RequestBody String requestBody) {
        MessageDto messageDto = parsePayload(requestBody);
        UUID toRoomId = UUID.fromString(messageDto.getToRoomId());
//        messageService.saveFromDto(messageDto);
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
