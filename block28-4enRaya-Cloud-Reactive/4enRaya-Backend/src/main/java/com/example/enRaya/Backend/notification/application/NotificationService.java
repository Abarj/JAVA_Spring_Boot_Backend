package com.example.enRaya.Backend.notification.application;

import com.example.enRaya.Backend.notification.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getLastNotifications();
    void saveNotification(Notification notification);
}
