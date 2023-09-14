package com.example.block28SpringBootWebSocketRealTimeChat.application;

import com.example.block28SpringBootWebSocketRealTimeChat.entities.documents.Message;

import java.util.List;

public interface ChatService {

    List<Message> getLast10Messages();
    Message save(Message message);
}
