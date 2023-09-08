package com.example.enRaya.Backend.notification.application;

import com.example.enRaya.Backend.notification.entity.Notification;
import com.example.enRaya.Backend.notification.infrastructure.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> getLastNotifications() {
        return notificationRepository.findFirst10ByOrderByDateDesc();
    }

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }
}
