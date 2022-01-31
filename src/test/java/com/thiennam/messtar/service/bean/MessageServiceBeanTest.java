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

}