package com.thiennam.messtar.controller;

import com.sun.org.apache.xpath.internal.operations.Mult;
import com.thiennam.messtar.entity.Resource;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.service.ResourceService;
import com.thiennam.messtar.service.StorageService;
import com.thiennam.messtar.service.UserService;
import com.thiennam.messtar.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Locale;

@Controller
public class ResourceController {

    @Autowired
    StorageService storageService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    UserService userService;

    @GetMapping("/upload")
    public String getUploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String handleUpload(@RequestParam("file") MultipartFile file) {
        boolean error;
        if (!file.isEmpty()) {
            User uploader = getLoggedUser();
            LocalDateTime now = LocalDateTime.now();
            Path path = storageService.getPath(now);
            // save info to db
            Resource resource = resourceService.init(uploader, file, path.toString(), now);
            resource = resourceService.save(resource);
            error = !storageService.saveFile(resource.getId().toString().toLowerCase(), file, path);
        } else {
            error = true;
        }
        return error ? "error" : "oke";
    }

    @PostMapping("/upload/multiple")
    @ResponseBody
    public String handleUpload(@RequestParam("files") MultipartFile[] files) {
        return "oke";
    }

    private User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUser = auth.getName();
        return userService.findByUsername(loggedUser);
    }
}
