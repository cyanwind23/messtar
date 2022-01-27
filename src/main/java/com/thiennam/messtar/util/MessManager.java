package com.thiennam.messtar.util;

import com.google.gson.GsonBuilder;
import com.thiennam.messtar.entity.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessManager {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendToUser(String toUser, MessageDto messageDto) {
        simpMessagingTemplate.convertAndSendToUser(toUser, "/queue/message", convert2Json(messageDto));
    }

    public void sendToRoom(UUID toRoomId, MessageDto messageDto) {
        simpMessagingTemplate.convertAndSend("/room/" + toRoomId, convert2Json(messageDto));
    }

    private String convert2Json(MessageDto messageDto) {
        return new GsonBuilder().create().toJson(messageDto);
    }

}
