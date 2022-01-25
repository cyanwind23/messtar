package com.thiennam.messtar.repository;

import com.thiennam.messtar.entity.Message;
import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.UserMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserMessageRepository extends JpaRepository<UserMessage, UUID> {
    UserMessage findByMessageAndUser(Message message, User user);
}