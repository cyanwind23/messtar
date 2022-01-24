package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.*;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.repository.MessageRepository;
import com.thiennam.messtar.service.MessageService;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(value = MessageService.NAME)
public class MessageServiceBean implements MessageService {

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessageDto toMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();

        messageDto.setContent(message.getContent());
        messageDto.setToRoomId(message.getRoom().getId().toString());
        messageDto.setSender(message.getSender().getUsername());
        messageDto.setType(message.getType().getId());

        return messageDto;
    }

    @Override
    public Message toMessage(MessageDto messageDto) {
        Room room = roomService.findById(UUID.fromString(messageDto.getToRoomId()));
        User sender = userService.findByUsername(messageDto.getSender());

        Message message = new Message();
        message.setSender(sender);
        message.setRoom(room);
        message.setPinned(false);
        message.setType(MessageTypeEnum.fromId(messageDto.getType()));
        message.setContent(messageDto.getContent());

        LocalDateTime now = LocalDateTime.now();
        message.setModified(now);
        message.setCreatedTime(now);
        // Also set for room
        room.setLastActive(now);

        return message;
    }

    @Override
    public void saveFromDto(MessageDto messageDto) {
        // TODO: can be separate to one more which save for SINGLE room, when toUser != null
        Message message = toMessage(messageDto);
        User sender = message.getSender();
        // create user - message relationship
        List<UserMessage> userMessages = new ArrayList<>();
        List<RoomUser> roomUsers = roomService.findUsersByRoom(message.getRoom());
        for (RoomUser roomUser : roomUsers) {
            User user = roomUser.getUser();
            UserMessage userMessage = new UserMessage();
            userMessage.setMessage(message);
            userMessage.setUser(user);
            userMessage.setReplyTo(null);
            if (user.equals(sender)) {
                userMessage.setStatus(UserMessageStatusEnum.SEEN);
            } else {
                userMessage.setStatus(UserMessageStatusEnum.UNSEEN);
            }
            userMessages.add(userMessage);
        }
        message.setUserMessages(userMessages);
        messageRepository.save(message);
    }
}
