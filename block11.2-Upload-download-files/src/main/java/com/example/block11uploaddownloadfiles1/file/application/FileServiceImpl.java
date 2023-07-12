package com.example.block11uploaddownloadfiles1.file.application;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import com.example.block11uploaddownloadfiles1.file.domain.File;
import com.example.block11uploaddownloadfiles1.file.infrastructure.repository.FileRepository;
import com.example.block11uploaddownloadfiles1.storage.exceptions.StorageException;
import com.example.block11uploaddownloadfiles1.storage.exceptions.StorageFileNotFoundException;
import com.example.block11uploaddownloadfiles1.storage.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    StorageProperties storageProperties;

    private Path root;

    @Value("${fileRoute}")
    private String fileRoute;

    @Autowired
    public FileServiceImpl(StorageProperties properties) {
        this.root = Paths.get(properties.getLocation());
    }

    @Override
    public File store(MultipartFile file) {
        Path finalLocation = this.root;
        if (!fileRoute.isBlank()) {
            finalLocation = Paths.get(fileRoute);
        }
        try {
            if (file.isEmpty()) {
                throw new StorageException("Error al intentar almacenar un fichero vacío");
            }

            Path destinationFile = finalLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(finalLocation.toAbsolutePath())) {
                throw new StorageException("No se puede almacenar el archivo fuera del directorio actual");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                File fichero = new File();
                fichero.setName(file.getOriginalFilename());
                fichero.setCategory(file.getContentType());
                fichero.setUploadDate(Date.valueOf(LocalDate.now()).toString());

                return fileRepository.save(fichero);
            }
        } catch (IOException e) {
            throw new StorageException("Error al almacenar el archivo", e);
        }
    }

    @Override
    public Path load(String fileName) {
        return root.resolve(fileName);
    }

    @Override
    public Resource loadAsResourceById(Integer id) throws StorageFileNotFoundException {
        try {
            Optional<File> optionalFile = fileRepository.findById(id);

            if (optionalFile.isEmpty())
                throw new StorageFileNotFoundException("No se ha encontrado un fichero con el id: " + id);

            String fichero = optionalFile.get().getName();
            Path file = load(fichero);
            Resource resource = null;

            resource = new UrlResource(file.toUri());


            if (!resource.exists() || !resource.isReadable()) {
                throw new StorageFileNotFoundException("No se ha podido leer el ficharo con el id: " + id);
            }

            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource loadAsResourceByFilename(String fileName) throws StorageFileNotFoundException {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new StorageFileNotFoundException("No se ha podido leer el ficharo con el id: " + fileName);
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("No se ha podido leer el ficharo con el id: " + fileName, e);
        }
    }

    @Override
    public void setPath(String path) {
        storageProperties.setLocation(path);
        this.root = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        }
        catch (IOException e) {
            throw new StorageException("No se ha podido iniciar el almacenamiento", e);
        }
    }
}