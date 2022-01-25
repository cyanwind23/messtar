package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.MessageDto;

import java.util.List;

public interface MessageService {
    String NAME = "messtar_MessageService";

    MessageDto toMessageDto(Message message, User user);

    List<MessageDto> toMessageDto(List<Message> messages, User user);

    Message toMessage(MessageDto messageDto);

    void saveFromDto(MessageDto messageDto);

    List<Message> find300LatestByRoom(Room room);
}
