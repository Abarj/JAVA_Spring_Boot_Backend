package com.example.block25SpringCloudDiscoveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Block25SpringCloudDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block25SpringCloudDiscoveryServerApplication.class, args);
    }
}
