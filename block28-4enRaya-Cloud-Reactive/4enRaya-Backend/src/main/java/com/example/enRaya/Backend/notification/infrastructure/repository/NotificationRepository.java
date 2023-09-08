package com.example.enRaya.Backend.notification.infrastructure.repository;

import com.example.enRaya.Backend.notification.entity.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository  extends ReactiveMongoRepository<Notification, UUID> {
    List<Notification> findFirst10ByOrderByDateDesc();
}
