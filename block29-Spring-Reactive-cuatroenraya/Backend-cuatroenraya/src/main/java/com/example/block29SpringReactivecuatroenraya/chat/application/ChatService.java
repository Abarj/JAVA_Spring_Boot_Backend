package com.example.block29SpringReactivecuatroenraya.chat.application;

import com.example.block29SpringReactivecuatroenraya.chat.entity.documents.Message;

import java.util.List;

public interface ChatService {

    List<Message> getLast10Messages();
    Message save(Message message);
}
