package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomTypeEnum;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceBeanTest {
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;

    @Test
    public void testAddNewRoom() {
        User user1 = userService.findByUsername("user");
        User user2 = userService.findByUsername("thiennam23");
        Room roomfn = roomService.findSingleRoomBy2User(user1, user2);

        List<Room> roomList1 = roomService.findByUser(user1);
        List<Room> roomList2 = roomService.findByUser(user2);

        for (Room room : roomList1) {
            if (room.getType().equals(RoomTypeEnum.SINGLE) && roomList2.contains(room)) {
                roomfn = room;
                break;
            }
        }
        Assert.notNull(roomfn, "room must not be null");
    }
}