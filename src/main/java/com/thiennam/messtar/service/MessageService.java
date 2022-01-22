package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.dto.MessageDto;

public interface MessageService {
    String NAME = "messtar_MessageService";

    MessageDto toMessageDto(Message message);

    Message toMessage(MessageDto messageDto);
}
