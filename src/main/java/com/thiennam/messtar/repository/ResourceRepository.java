package com.thiennam.messtar.repository;

import com.thiennam.messtar.entity.MesStarResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceRepository extends JpaRepository<MesStarResource, UUID> {
}