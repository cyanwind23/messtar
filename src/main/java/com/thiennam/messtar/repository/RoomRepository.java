package com.thiennam.messtar.repository;

import com.thiennam.messtar.entity.Room;
import com.thiennam.messtar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

}