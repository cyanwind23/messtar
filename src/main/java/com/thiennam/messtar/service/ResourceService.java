package com.thiennam.messtar.service;

import com.thiennam.messtar.entity.Resource;
import com.thiennam.messtar.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface ResourceService {
    String NAME = "messtar_ResourceService";
    Resource save(Resource resource);

    Resource init(User uploader, MultipartFile file, String dest, LocalDateTime createdTime);
}
