package com.example.block11uploaddownloadfiles1;

import com.example.block11uploaddownloadfiles1.file.application.FileService;
import com.example.block11uploaddownloadfiles1.storage.properties.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Block11UploadDownloadFiles1Application {

	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFiles1Application.class, args);
	}
	@Bean
	CommandLineRunner init(FileService fileService) {
		return (args) -> {
			fileService.deleteAll();
			fileService.init();
		};
	}
}
