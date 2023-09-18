package com.example.block29SpringReactivecuatroenraya.session.infrastructure.controller;

import com.example.block29SpringReactivecuatroenraya.player.application.PlayerServiceImpl;
import com.example.block29SpringReactivecuatroenraya.session.application.SessionServiceImpl;
import com.example.block29SpringReactivecuatroenraya.session.entity.Metadata;
import com.example.block29SpringReactivecuatroenraya.session.entity.Session;
import com.example.block29SpringReactivecuatroenraya.session.infrastructure.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BoardController {

    @Autowired
    SessionServiceImpl sessionServiceImpl;

    @Autowired
    PlayerServiceImpl playerService;

    @MessageMapping("/movement")
    @SendTo("/board/movement")
    public Session movement(Metadata metadata){
        Session session = sessionServiceImpl.getSessionById(String.valueOf(metadata.getSessionId())).block();

        if(metadata.getPlayerId().equals(session.getPlayerOne()) && metadata.getRound() % 2 != 0 || metadata.getPlayerId().equals(session.getPlayerTwo()) && metadata.getRound() % 2 == 0) {
            System.out.println(metadata.getPlayerId() + " " + metadata.getSessionId() + " " + metadata.getColumn() + " " + metadata.getRound());
            SessionUtils.putToken(session, playerService.findByUsername(String.valueOf(metadata.getPlayerId())).block(), metadata.getColumn());

            sessionServiceImpl.updateSession(session);
            return session;
        }
        else{
            return new Session();
        }
    }
}
