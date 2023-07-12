package com.example.block11uploaddownloadfiles1.file.application;

import com.example.block11uploaddownloadfiles1.file.domain.File;
import com.example.block11uploaddownloadfiles1.file.exceptions.FileException;
import com.example.block11uploaddownloadfiles1.storage.exceptions.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileService {

    File store(MultipartFile file);
    Path load(String fileName);
    Resource loadAsResourceById(Integer id) throws StorageFileNotFoundException;
    Resource loadAsResourceByFilename(String fileName) throws StorageFileNotFoundException;
    void setPath(String path);
    void deleteAll();
    void init();

}
