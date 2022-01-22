package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service(value = MessageService.NAME)
public class MessageServiceBean implements MessageService {
    @Override
    public MessageDto toMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();

        messageDto.setContent(message.getContent());
        messageDto.setToRoomId(message.getRoom().getId().toString());
        messageDto.setSenderName(message.getSender().getUsername());
        messageDto.setType(message.getType());

        return messageDto;
    }

    @Override
    public Message toMessage(MessageDto messageDto) {
        Message message = new Message();

        message.setContent(messageDto.getContent());
//        message.setDeleted(false);
//        message.setType();
//        message.setRoom();
//        message.setSender(userService.findByUsername(messageDto.getSenderName()));
        message.setCreatedTime(LocalDateTime.now());

        return message;
    }
}
