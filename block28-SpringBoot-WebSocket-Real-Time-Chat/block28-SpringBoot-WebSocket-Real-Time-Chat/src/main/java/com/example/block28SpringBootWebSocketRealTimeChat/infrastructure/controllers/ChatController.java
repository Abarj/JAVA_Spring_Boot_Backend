package com.example.block28SpringBootWebSocketRealTimeChat.infrastructure.controllers;

import com.example.block28SpringBootWebSocketRealTimeChat.application.ChatService;
import com.example.block28SpringBootWebSocketRealTimeChat.entities.documents.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private final String[] colors = {"greenyellow", "deeppink", "orange", "darkviolet", "purple", "teal", "aqua", "olive", "salmon"};

    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message receiveMessage(Message message) {
        message.setDate(new Date().getTime());

        if(message.getType().equals("NEW_USER")) {
            message.setColor(colors[new Random().nextInt(colors.length)]);
            message.setText("New user");
            chatService.save(message);
        } else {
            chatService.save(message);
        }

        return message;
    }

    @MessageMapping("/writing")
    @SendTo("/chat/writing")
    public String isWriting(String username) {
        return username.concat(" is writing...");
    }

    @MessageMapping("/history")
    public void history(String clientId) {
        simpMessagingTemplate.convertAndSend("/chat/history/" + clientId, chatService.getLast10Messages());
    }
}
