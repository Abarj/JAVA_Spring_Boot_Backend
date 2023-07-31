package com.example.block17.SpringBatchApplication.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> receiveFile(@RequestParam(name = "file") MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();

        try {
            Path path = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "files" + File.separator + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            log.info("----------> Start of BATCH PROCESS <----------");

            // // Crear JobParameters -> Se puede acceder a estos parámetros desde todos los Tasklets
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("Date", new Date())
                    .addString("fileName", fileName)
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);

            Map<String, String> response = new HashMap<>();
            response.put("File", fileName);
            response.put("Status", "Received");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error starting the batch process: Error {}", e.getMessage());
            throw new RuntimeException();
        }
    }
}
