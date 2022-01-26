package com.thiennam.messtar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.UserMessageStatusEnum;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.service.MessageService;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Message message = messageService.saveFromDto(messageDto);
        prepareToSend(message, messageDto);
        simpMessagingTemplate.convertAndSendToUser(messageDto.getToUser(), "/queue/message", convert2Json(messageDto));
        return "{\"data\" : \"send OK\"}";
    }

    @PostMapping("/room")
    @ResponseBody
    public String sendMessageToRoom(@RequestBody String requestBody) {
        MessageDto messageDto = parsePayload(requestBody);
        UUID toRoomId = UUID.fromString(messageDto.getToRoomId());
        Message message = messageService.saveFromDto(messageDto);
        prepareToSend(message, messageDto);
        simpMessagingTemplate.convertAndSend("/room/" + toRoomId, convert2Json(messageDto));
        return "{\"data\" : \"OK\"}";
    }

    private MessageDto parsePayload(String payload) {
        if (gson == null) {
            gson = new GsonBuilder().create();
            Logger logger = LoggerFactory.getLogger(MessageController.class);
            logger.info("create GSON");
        }
        return gson.fromJson(payload, MessageDto.class);
    }

    private String convert2Json(MessageDto messageDto) {
        return new GsonBuilder().create().toJson(messageDto);
    }
    private void prepareToSend(Message message, MessageDto messageDto) {
        if (message != null && messageDto != null) {
            messageDto.setCreatedMillis(DateTimeUtil.toMillis(message.getCreatedTime()));
            messageDto.setModifiedMillis(DateTimeUtil.toMillis(message.getModified()));
            messageDto.setStatus(UserMessageStatusEnum.UNSEEN.getId());
        }
    }

}
