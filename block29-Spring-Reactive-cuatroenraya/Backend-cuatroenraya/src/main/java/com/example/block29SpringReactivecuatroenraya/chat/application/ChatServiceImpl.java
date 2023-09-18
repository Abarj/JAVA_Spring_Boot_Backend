package com.example.block29SpringReactivecuatroenraya.chat.application;

import com.example.block29SpringReactivecuatroenraya.chat.entity.documents.Message;
import com.example.block29SpringReactivecuatroenraya.chat.infrastructure.repository.ChatRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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
