package com.example.enRaya.Backend.session.infrastructure.controller;

import com.example.enRaya.Backend.session.application.SessionServiceImpl;
import com.example.enRaya.Backend.session.entity.Session;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/session")
@CrossOrigin
@AllArgsConstructor
public class SessionController {

    private final SessionServiceImpl sessionService;

    @PostMapping
    public Session newSession(@RequestBody Session session){
        return sessionService.newSession(session);
    }

    @PostMapping("/playerTwo/{sessionId}/{playerTwo}")
    public Mono<Session> addPlayerToSession(@PathVariable UUID sessionId, @PathVariable String playerTwo){
        return sessionService.addPlayerToSession(sessionId, playerTwo);
    }

    @GetMapping("/all")
    public Flux<Session> getAllSessions(){
        return sessionService.getAllSessions();
    }

    @GetMapping("/{sessionId}")
    public Mono<Session> getSessionById(@PathVariable("sessionId") UUID sessionId){
        return sessionService.getSessionById(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public Mono<Void> deleteSession(@PathVariable UUID sessionId){
        return sessionService.deleteSession(sessionId);
    }
}
