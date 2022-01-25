package com.thiennam.messtar.repository;

import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findTop300ByRoomOrderByCreatedTimeDesc(Room room);
}