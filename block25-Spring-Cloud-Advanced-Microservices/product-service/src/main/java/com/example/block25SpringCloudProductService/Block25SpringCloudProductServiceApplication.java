package com.example.block25SpringCloudProductService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Block25SpringCloudProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block25SpringCloudProductServiceApplication.class, args);
	}
}
