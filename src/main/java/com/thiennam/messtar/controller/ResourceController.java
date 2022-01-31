package com.thiennam.messtar.controller;

import com.thiennam.messtar.entity.MesStarResource;
import com.thiennam.messtar.entity.User;
import com.thiennam.messtar.service.ResourceService;
import com.thiennam.messtar.service.StorageService;
import com.thiennam.messtar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

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
            MesStarResource mesStarResource = resourceService.init(uploader, file, path.toString(), now);
            mesStarResource = resourceService.save(mesStarResource);
            error = !storageService.saveFile(mesStarResource.getId().toString().toLowerCase(), file, path);
        } else {
            error = true;
        }
        return error ? "error" : "oke";
    }

    @PostMapping("/upload/multiple")
    @ResponseBody
    public String handleUpload(@RequestParam("files") MultipartFile[] files) {
        // TODO: method need to be written
        return "oke";
    }

    private User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUser = auth.getName();
        return userService.findByUsername(loggedUser);
    }

    @GetMapping("/storage")
    @ResponseBody
    public ResponseEntity<Resource> getResource(@RequestParam("rid") String rid) {
        MesStarResource res = resourceService.findById(UUID.fromString(rid));
        if (res != null) {
            Resource file = storageService.getFile(res);
            return ResponseEntity.ok().header(
                    HttpHeaders.CONTENT_TYPE, res.getType())
                    .body(file);
        }
        return ResponseEntity.internalServerError().body(null);
    }

    @GetMapping("/storage/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadResource(@RequestParam("rid") String rid) {
        MesStarResource res = resourceService.findById(UUID.fromString(rid));
        if (res != null) {
            Resource file = storageService.getFile(res);
            return ResponseEntity.ok().header(
                    HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + res.getName() + "\"")
                    .body(file);
        }
        return ResponseEntity.internalServerError().body(null);
    }
}
