package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.MesStarResource;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.repository.ResourceRepository;
import com.thiennam.messtar.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service(value = ResourceService.NAME)
public class ResourceServiceBean implements ResourceService {
    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public MesStarResource save(MesStarResource mesStarResource) {
        return resourceRepository.save(mesStarResource);
    }

    @Override
    public MesStarResource init(User uploader, MultipartFile file, String dest, LocalDateTime createdTime) {
        MesStarResource out = new MesStarResource();
        out.setName(file.getOriginalFilename());
        out.setType(file.getContentType());
        out.setOwner(uploader);
        out.setPath(dest);
        out.setCreatedTime(createdTime);

        return out;
    }

    @Override
    public MesStarResource findById(UUID id) {
        Optional<MesStarResource> res = resourceRepository.findById(id);
        return res.orElse(null);
    }
}
