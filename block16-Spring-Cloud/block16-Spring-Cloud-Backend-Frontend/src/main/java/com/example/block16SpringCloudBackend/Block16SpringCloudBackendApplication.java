package com.example.block16SpringCloudBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Block16SpringCloudBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16SpringCloudBackendApplication.class, args);
	}

}
