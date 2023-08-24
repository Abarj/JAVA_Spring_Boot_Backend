package com.example.block25SpringCloudNotificationService;

import com.example.block25SpringCloudNotificationService.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class Block25SpringCloudNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block25SpringCloudNotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        // Mandar notificación al email
        log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
    }
}