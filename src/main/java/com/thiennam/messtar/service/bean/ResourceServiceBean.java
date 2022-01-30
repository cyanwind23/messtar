package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.FileExtEnum;
import com.thiennam.messtar.entity.Resource;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.repository.ResourceRepository;
import com.thiennam.messtar.service.ResourceService;
import com.thiennam.messtar.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service(value = ResourceService.NAME)
public class ResourceServiceBean implements ResourceService {
    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource init(User uploader, MultipartFile file, String dest, LocalDateTime createdTime) {
        Resource out = new Resource();
        out.setName(file.getOriginalFilename());
        out.setType(FileExtEnum.fromId(ResourceUtil.getFileExt(file.getOriginalFilename())));
        out.setOwner(uploader);
        out.setPath(dest);
        out.setCreatedTime(createdTime);

        return out;
    }
}
