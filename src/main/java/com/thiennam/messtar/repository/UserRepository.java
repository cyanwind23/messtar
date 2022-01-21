package com.thiennam.messtar.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiennam.messtar.entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    User findByUsername(String username);
}
