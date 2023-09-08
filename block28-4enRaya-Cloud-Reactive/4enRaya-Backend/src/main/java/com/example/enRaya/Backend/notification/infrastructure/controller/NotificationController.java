package com.example.enRaya.Backend.notification.infrastructure.controller;

import com.example.enRaya.Backend.notification.application.NotificationService;
import com.example.enRaya.Backend.notification.entity.Notification;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    private final SimpMessagingTemplate webSocket;

    private String[] colors = {
            "RED",
            "BLUE",
            "ORANGE",
            "PURPLE",
            "GREEN",
            "YELLOW"
    };

    /*
    @MessageMapping -> anotación utilizada en Spring Framework, específicamente en Spring WebSocket
    para mapear mensajes WebSocket entrantes a métodos de controladores en una aplicación web.
     */

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Notification receiveNotification(Notification notification){
        notification.setDate(LocalDateTime.now());
        if (notification.getType().equals(("NEW_USER"))){
            notification.setColor(colors[new Random().nextInt(colors.length)]);
            notification.setText("New user");
        }else {notificationService.saveNotification(notification);}
        return notification;
    }


    @MessageMapping("/writing")
    @SendTo("/notification/writing")
    public String isWriting(String username){
        return username.concat("  is writing....");
    }

    @MessageMapping("/history")
    public void history(UUID playerId){
        webSocket.convertAndSend("/chat/history/" + playerId, notificationService.getLastNotifications());
    }
}
