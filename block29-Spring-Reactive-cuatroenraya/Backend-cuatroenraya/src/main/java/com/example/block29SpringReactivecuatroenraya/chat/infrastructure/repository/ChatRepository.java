package com.example.block29SpringReactivecuatroenraya.chat.infrastructure.repository;

import com.example.block29SpringReactivecuatroenraya.chat.entity.documents.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Message, String> {

    List<Message> findFirst10ByOrderByDateDesc();
}
