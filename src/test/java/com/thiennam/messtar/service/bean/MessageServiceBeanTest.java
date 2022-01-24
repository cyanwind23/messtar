package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.*;
import com.thiennam.messtar.entity.dto.MessageDto;
import com.thiennam.messtar.repository.MessageRepository;
import com.thiennam.messtar.service.MessageService;
import com.thiennam.messtar.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceBeanTest {
    @Autowired
    MessageService messageService;

    @Test
    void saveFromDto() {
        // Init message dto
        MessageDto messageDto = new MessageDto();
        messageDto.setSender("thiennam23");
        messageDto.setToRoomId("1aa8c000-4901-4ea3-a58d-e6ac92cfde04");
        messageDto.setType("TEXT");
        messageDto.setToUser("tiankoob23");
        messageDto.setContent("Hello");

        messageService.saveFromDto(messageDto);
    }
}