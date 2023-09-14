package com.example.block28SpringBootWebSocketRealTimeChat.infrastructure.repository;

import com.example.block28SpringBootWebSocketRealTimeChat.entities.documents.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Message, String> {

    List<Message> findFirst10ByOrderByDateDesc();
}
