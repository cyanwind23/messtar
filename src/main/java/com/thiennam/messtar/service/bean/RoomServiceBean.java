package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomTypeEnum;
import com.thiennam.messtar.entity.RoomUser;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.dto.RoomDto;
import com.thiennam.messtar.entity.dto.UserDto;
import com.thiennam.messtar.repository.RoomRepository;
import com.thiennam.messtar.repository.RoomUserRepository;
import com.thiennam.messtar.service.RoomService;
import com.thiennam.messtar.service.UserService;
import com.thiennam.messtar.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service(value = RoomService.NAME)
public class RoomServiceBean implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomUserRepository roomUserRepository;

    @Autowired
    UserService userService;

    @Override
    public void addNewSingleRoom(User user1, User user2) {
        // check if room exist
        Room room = findSingleRoomBy2User(user1, user2);
        if (room == null) {
            room = createNewRoom(Arrays.asList(user1, user2), null, RoomTypeEnum.SINGLE, null);
            if (room != null) {
                roomRepository.save(room);
            }
        }
    }

    @Override
    public void addNewMultipleRoom(List<User> users, String roomName, String description) {
        // check valid room: number of user > 2
        if (users.size() > 2) {
            Room room = createNewRoom(users, roomName, RoomTypeEnum.MULTIPLE, description);
            if (room != null) {
                roomRepository.save(room);
            }
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
    public List<Room> findByUserAndTypeSorted(User user, RoomTypeEnum roomType) {
        List<Room> rooms = findByUser(user);
        rooms.sort(Comparator.comparing(Room::getLastActive).reversed());
        List<Room> filtered = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType().equals(roomType)) {
                filtered.add(room);
            }
        }
        return filtered;
    }

    @Override
    public List<Room> findByUserAndType(User user, RoomTypeEnum roomType) {
        List<Room> rooms = findByUser(user);
        List<Room> filtered = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType().equals(roomType)) {
                filtered.add(room);
            }
        }
        return filtered;
    }

    private void setNameForSingleRoom(Room room, User user) {
        for (RoomUser roomUser : room.getRoomUsers()) {
            if (!roomUser.getUser().equals(user)) {
                room.setName(roomUser.getUser().getName());
                break;
            }
        }
    }

    @Override
    public User findOtherInSingleRoom(Room room, User user) {
        if (room.getType().equals(RoomTypeEnum.SINGLE)) {
            return roomRepository.findOtherUserInSingleRoom(room, user);
        }
        return null;
    }

    @Override
    public Room findById(UUID id) {
        Optional<Room> res = roomRepository.findById(id);
        return res.orElse(null);
    }

    @Override
    public List<RoomDto> toDto(List<Room> rooms) {
        List<RoomDto> out = new ArrayList<>();
        for (Room room : rooms) {
            out.add(toDto(room));
        }
        return out;
    }

    @Override
    public RoomDto toDto(Room room) {
        RoomDto out = new RoomDto();
        out.setRoomId(room.getId().toString());
        out.setName(room.getName());
        out.setDescription(room.getDescription());
        out.setCreatedTime(DateTimeUtil.toMillis(room.getCreatedTime()));
        out.setLastActive(DateTimeUtil.toMillis(room.getLastActive()));
        out.setType(room.getType().getId());

        List<String> users = new ArrayList<>();
        for (RoomUser roomUser : room.getRoomUsers()) {
            users.add(roomUser.getUser().getUsername());
        }
        out.setRoomUsers(users);
        return out;
    }

    @Nullable
    private Room createNewRoom(List<User> users, @Nullable String roomName, RoomTypeEnum roomType, @Nullable String description) {
        if (users.isEmpty()) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        Room room = new Room();
        room.setName(roomName);
        room.setType(roomType);
        room.setDescription(description);
        room.setCreatedTime(now);
        room.setLastActive(now);

        List<RoomUser> roomUsers = new ArrayList<>();
        for (User user : users) {
            roomUsers.add(createRoomUser(user, room));
        }
        room.setRoomUsers(roomUsers);
        return room;
    }

    private RoomUser createRoomUser(User user, Room room) {
        RoomUser roomUser = new RoomUser();
        roomUser.setUser(user);
        roomUser.setRoom(room);
        return roomUser;
    }

    @Override
    public List<RoomUser> findUsersByRoom(Room room) {
        return roomUserRepository.findByRoom(room);
    }

    @Override
    public RoomDto prepareSingleRoomDtos(Room room, User user) {
        User friend = roomRepository.findOtherUserInSingleRoom(room, user);
        UserDto userDto = userService.toUserDto(friend);

        RoomDto roomDto = toDto(room);
        // display as friend name
        roomDto.setName(friend.getName());
        roomDto.setUserDto(userDto);
        return roomDto;
    }

    @Override
    public List<RoomDto> prepareSingleRoomDtos(List<Room> rooms, User user) {
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            roomDtos.add(prepareSingleRoomDtos(room, user));
        }
        return roomDtos;
    }
}
