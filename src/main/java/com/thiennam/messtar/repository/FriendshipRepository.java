package com.thiennam.messtar.repository;

import com.thiennam.messtar.entity.Friendship;
import com.thiennam.messtar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {
    List<Friendship> findByUser1(User user);
}