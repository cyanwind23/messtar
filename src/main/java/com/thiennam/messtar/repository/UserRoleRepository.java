package com.thiennam.messtar.repository;

import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    List<UserRole> findByUser(User user);
}