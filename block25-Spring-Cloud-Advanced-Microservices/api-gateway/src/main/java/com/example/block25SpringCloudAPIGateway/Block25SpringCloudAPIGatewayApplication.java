package com.example.block25SpringCloudAPIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Block25SpringCloudAPIGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block25SpringCloudAPIGatewayApplication.class, args);
    }
}
