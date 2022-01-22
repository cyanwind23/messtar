package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomTypeEnum;
import com.thiennam.messtar.entity.RoomUser;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.repository.RoomRepository;
import com.thiennam.messtar.repository.RoomUserRepository;
import com.thiennam.messtar.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service(value = RoomService.NAME)
public class RoomServiceBean implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomUserRepository roomUserRepository;

    @Override
    public void addNewSingleRoom(User user1, User user2) {
        // check if room exist
        Room room = findSingleRoomBy2User(user1, user2);
        if (room == null) {
            room = new Room();
            room.setType(RoomTypeEnum.SINGLE);
            room.setCreatedTime(LocalDateTime.now());

            RoomUser roomUser1 = new RoomUser();
            roomUser1.setUser(user1);
            roomUser1.setRoom(room);

            RoomUser roomUser2 = new RoomUser();
            roomUser2.setUser(user2);
            roomUser2.setRoom(room);

            List<RoomUser> roomUsers = Arrays.asList(roomUser1, roomUser2);
            room.setRoomUsers(roomUsers);

            roomRepository.save(room);
        }
    }

    @Override
    public Room findSingleRoomBy2User(User user1, User user2) {
        List<Room> roomList1 = findByUser(user1);
        List<Room> roomList2 = findByUser(user2);

        roomList1.retainAll(roomList2);
        for (Room room : roomList1) {
            if (room.getType().equals(RoomTypeEnum.SINGLE)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public List<Room> findByUser(User user) {
        List<RoomUser> roomUsers = roomUserRepository.findByUser(user);
        List<Room> rooms = new ArrayList<>();
        for (RoomUser roomUser : roomUsers) {
            rooms.add(roomUser.getRoom());
        }
        return rooms;
    }

    @Override
    public Room findById(UUID toRoomId) {
        Optional<Room> res = roomRepository.findById(toRoomId);
        return res.orElse(null);
    }
}
