package com.thiennam.messtar.service.bean;

import com.thiennam.messtar.entity.MesStarResource;
import com.thiennam.messtar.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service(value = StorageService.NAME)
public class StorageServiceBean implements StorageService {
    @Override
    public boolean saveFile(String fileName, MultipartFile file, Path dest) {
        dest = dest.resolve(fileName).normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    // TODO: can return a list of all files which failed to save
    public void saveFiles(List<MultipartFile> files, Path dest) {

    }

    @Override
    public Path getPath(LocalDateTime now) {
        Path path = Paths.get(createPath(now));
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            }
            catch (Exception e) {
                // TODO: turn off e.printStackTrace(); later
                e.printStackTrace();
                return null;
            }
        }
        return path;
    }

    @Override
    public Resource getFile(MesStarResource res) {
        try {
            Path path = Paths.get(res.getPath() + "/" + res.getId());

            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                System.out.println("Could not read file: ");
            }
        }
        catch (MalformedURLException e) {
            System.out.println("Could not read file: ");
        }
        return null;
    }

    private String createPath(LocalDateTime now) {
        return String.format("storage/%04d/%02d/%02d", now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());
    }
}
