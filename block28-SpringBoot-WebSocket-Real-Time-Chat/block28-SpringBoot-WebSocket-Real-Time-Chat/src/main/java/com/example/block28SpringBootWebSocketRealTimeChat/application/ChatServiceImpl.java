package com.example.block28SpringBootWebSocketRealTimeChat.application;

import com.example.block28SpringBootWebSocketRealTimeChat.entities.documents.Message;
import com.example.block28SpringBootWebSocketRealTimeChat.infrastructure.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Message> getLast10Messages() {
        return chatRepository.findFirst10ByOrderByDateDesc();
    }

    @Override
    public Message save(Message message) {
        return chatRepository.save(message);
    }
}
