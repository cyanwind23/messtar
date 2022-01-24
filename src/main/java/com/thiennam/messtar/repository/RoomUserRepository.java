package com.thiennam.messtar.repository;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.RoomUser;
import com.thiennam.messtar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomUserRepository extends JpaRepository<RoomUser, UUID> {
    List<RoomUser> findByUser(User user);

    List<RoomUser> findByRoom(Room room);
}