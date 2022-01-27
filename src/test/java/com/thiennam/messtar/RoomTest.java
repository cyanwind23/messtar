package com.thiennam.messtar;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
public class RoomTest {
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;

    @Test
    public void addMultipleRoom() {
        User user1 = userService.findByUsername("thiennam23");
        User user2 = userService.findByUsername("user");
        User user3 = userService.findByUsername("tiankoob23");
        roomService.addNewMultipleRoom(Arrays.asList(user1, user2, user3), "Group MesStar", "This is first multiple room of MesStar");
    }

    @Test
    public void getOtherInSingleRoomTest() {
        User user1 = userService.findByUsername("thiennam23");
        Room room = roomService.findById(UUID.fromString("1aa8c000-4901-4ea3-a58d-e6ac92cfde04"));
        User user = roomService.findOtherInSingleRoom(room, user1);
        System.out.println(user.getName());
    }
}
