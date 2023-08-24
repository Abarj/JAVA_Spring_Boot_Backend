package com.example.block25SpringCloudOrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Block25SpringCloudOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block25SpringCloudOrderServiceApplication.class, args);
	}
}
