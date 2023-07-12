package com.example.block11uploaddownloadfiles1.file.infrastructure.controller;

import com.example.block11uploaddownloadfiles1.file.application.FileService;
import com.example.block11uploaddownloadfiles1.file.domain.File;
import com.example.block11uploaddownloadfiles1.file.exceptions.FileException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping
    public ResponseEntity<File> uploadAnyFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) throws FileException {
        try {
            redirectAttributes.addFlashAttribute("mensaje", "Has subido satisfactoriamente " + file.getName() + "!");
            return ResponseEntity.ok().body(fileService.store(file));

        } catch (FileException e) {
            throw new FileException(e.getMessage());
        }
    }

    @PostMapping("/upload/{type}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String type) {
        try {
            String fileName = file.getOriginalFilename().toString();
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);

            if (!type.equals(fileType)) {
                return ResponseEntity.badRequest().body("Únicamente puede subir fichero tipo '" + type + "'");
            }

            return ResponseEntity.ok().body(fileService.store(file));

        } catch (FileException e) {
            throw new FileException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable Integer id) {
        Resource file = null;
        try {
            file = fileService.loadAsResourceById(id);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (FileException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filename/{name}")
    public ResponseEntity<Resource> downloadFileByFilename(@PathVariable String name) {
        try {
            Resource file = fileService.loadAsResourceByFilename(name);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (FileException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/setpath/{path}")
    public ResponseEntity<String> setPath(@PathVariable String path) {
        fileService.setPath(path);
        fileService.init();
        return ResponseEntity.ok().body("El nuevo directorio es: " + path);
    }
}