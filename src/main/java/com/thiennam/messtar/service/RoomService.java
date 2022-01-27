package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomTypeEnum;
import com.thiennam.messtar.entity.RoomUser;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.RoomDto;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    String NAME = "messtar_RoomService";

    void addNewSingleRoom(User user1, User user2);

    void addNewMultipleRoom(List<User> users, String roomName, String description);

    Room findSingleRoomBy2User(User user1, User user2);

    List<Room> findByUser(User user);

    List<Room> findByUserAndTypeSorted(User user, RoomTypeEnum roomType);

    List<Room> findByUserAndType(User user, RoomTypeEnum roomType);

    User findOtherInSingleRoom(Room room, User user);

    Room findById(UUID id);

    List<RoomDto> toDto(List<Room> rooms);

    RoomDto toDto(Room room);

    List<RoomUser> findUsersByRoom(Room room);
}
