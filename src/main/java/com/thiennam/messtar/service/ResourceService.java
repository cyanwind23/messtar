package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.MesStarResource;
import com.thiennam.messtar.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ResourceService {
    String NAME = "messtar_ResourceService";
    MesStarResource save(MesStarResource mesStarResource);

    MesStarResource init(User uploader, MultipartFile file, String dest, LocalDateTime createdTime);

    MesStarResource findById(UUID fromString);
}
