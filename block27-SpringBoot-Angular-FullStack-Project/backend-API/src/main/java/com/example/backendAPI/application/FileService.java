package com.example.backendAPI.application;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface FileService {

    Resource loadImage(String fileName) throws MalformedURLException;
    String saveImage(MultipartFile file) throws IOException;
    boolean delete(String fileName);
    Path getPath(String fileName);
}
