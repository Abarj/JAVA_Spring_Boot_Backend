package com.example.block29SpringReactivecuatroenraya.session.infrastructure.controller;

import com.example.block29SpringReactivecuatroenraya.player.application.PlayerServiceImpl;
import com.example.block29SpringReactivecuatroenraya.session.application.SessionService;
import com.example.block29SpringReactivecuatroenraya.session.entity.Session;
import com.example.block29SpringReactivecuatroenraya.session.infrastructure.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/session")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @Autowired
    PlayerServiceImpl playerService;

    @PostMapping("/{column}/{playerId}/{sessionId}")
    public boolean movement(@PathVariable int column, @PathVariable String playerId, @PathVariable String sessionId) {
        Session session = sessionService.getSessionById(sessionId).block();
        SessionUtils.showActualBoard(session);
        boolean result = SessionUtils.putToken(session, playerService.findByUsername(playerId).block(), column);
        sessionService.updateSession(session);
        SessionUtils.showActualBoard(session);
        return result;
    }

    @PostMapping
    public Session newSession(@RequestBody Session session){
        return sessionService.newSession(session);
    }

    @GetMapping("/{sessionId}")
    public Mono<Session> getSessionById(@PathVariable("sessionId") String sessionId){
        return sessionService.getSessionById(sessionId);
    }

    @GetMapping("/all")
    public Flux<Session> getAllSessions(){
        return sessionService.getAllSessions();
    }

    @DeleteMapping("/{sessionId}")
    public Mono<Void> deleteSession(@PathVariable String sessionId){
        return sessionService.deleteSession(sessionId);
    }

    @PostMapping("/playerTwo/{sessionId}/{playerTwo}")
    public Mono<Session> addPlayerTwo(@PathVariable String sessionId, @PathVariable String playerTwo){
        return sessionService.addPlayerTwo(sessionId, playerTwo);
    }
}