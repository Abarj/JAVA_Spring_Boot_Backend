package com.example.block16SpringCloudDiscoveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Block16SpringCloudDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16SpringCloudDiscoveryServerApplication.class, args);
	}

}
