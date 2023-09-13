package com.example.backendAPI.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public Resource loadImage(String avatarName) throws MalformedURLException {
        Path filePath = getPath(avatarName);
        log.info(filePath.toString());
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            filePath = Paths.get("backend-API/src/main/resources/static/images").resolve("no-user.png").toAbsolutePath();

            resource = new UrlResource(filePath.toUri());

            log.error("Could not load image: " + avatarName);
        }

        return resource;
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");

        Path filePath = getPath(fileName);
        log.info(filePath.toString());

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public boolean delete(String fileName) {
        if (fileName != null && fileName.length() > 0) {
            Path oldAvatarPath = Paths.get("backend-API", "uploads").resolve(fileName).toAbsolutePath();
            File oldAvatarFile = oldAvatarPath.toFile();

            if (oldAvatarFile.exists() && oldAvatarFile.canRead()) {
                oldAvatarFile.delete();

                return true;
            }
        }

        return false;
    }

    @Override
    public Path getPath(String avatarName) {
        return Paths.get(BACKEND_DIRECTORY, UPLOADS_DIRECTORY).resolve(Objects.requireNonNull(avatarName)).toAbsolutePath();
    }

    private final static String BACKEND_DIRECTORY = "backend-API";
    private final static String UPLOADS_DIRECTORY = "uploads";
}
