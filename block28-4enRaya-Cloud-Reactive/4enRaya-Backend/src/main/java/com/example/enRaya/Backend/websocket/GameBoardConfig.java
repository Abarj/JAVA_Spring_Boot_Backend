package com.example.enRaya.Backend.websocket;

import com.example.enRaya.Backend.session.application.SessionServiceImpl;
import com.example.enRaya.Backend.session.entity.Session;
import com.example.enRaya.Backend.player.application.PlayerServiceImpl;
import com.example.enRaya.Backend.session.entity.metadata.Data;
import com.example.enRaya.Backend.session.infrastructure.utils.SessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GameBoardConfig {

    private final SessionServiceImpl sessionService;

    private final PlayerServiceImpl playerService;

    @MessageMapping("/movement")
    public Session movement(Data metadata) {
        Session session = sessionService.getSessionById(metadata.getSessionId()).block();
        if (metadata.getPlayerId().equals(session.getPlayerOne()) && metadata.getTurn() % 2 != 0
                || metadata.getPlayerId().equals(session.getPlayerTwo()) && metadata.getTurn() % 2 == 0) {
            SessionUtils.putToken(session, playerService.findPlayerByUsername(metadata.getPlayerId()).block(), metadata.getColumn());

            sessionService.refreshSession(session);

            return session;
        }
        else {
            return new Session();
        }
    }
}
