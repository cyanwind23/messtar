package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.User;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    String NAME = "messtar_RoomService";

    void addNewSingleRoom(User user1, User user2);

    Room findSingleRoomBy2User(User user1, User user2);

    List<Room> findByUser(User user);

    Room findById(UUID toRoomId);
}
