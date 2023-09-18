package com.example.block29SpringReactivecuatroenraya.session.application;

import com.example.block29SpringReactivecuatroenraya.session.entity.Session;
import com.example.block29SpringReactivecuatroenraya.session.infrastructure.enums.Status;
import com.example.block29SpringReactivecuatroenraya.session.infrastructure.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class SessionServiceImpl implements SessionService{
    @Autowired
    SessionRepository sessionRepository;

    @Override // --- Create a new game session ---
    public Session newSession(Session session) {
        LocalDateTime ldt = LocalDateTime.now();
        session.setCreatedAt(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(ldt));
        session.setId(String.valueOf(new Random().nextInt(1,10000)));
        session.setStatus(Status.OPEN);

        return sessionRepository.save(session).block();
    }

    @Override // --- Get session by id ---
    public Mono<Session> getSessionById(String sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override // --- Get all available sessions ---
    public Flux<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override // --- Update session metadata ---
    public void updateSession(Session session) {
        sessionRepository.save(session).subscribe();
    }

    @Override // --- Delete session ---
    public Mono<Void> deleteSession(String sessionId) {
        return sessionRepository.deleteById(sessionId);
    }

    @Override // --- Add second player to session ---
    public Mono<Session> addPlayerTwo(String sessionId, String playerTwo) {
        Session session = sessionRepository.findById(sessionId).block();
        if(session.getPlayerTwo()==null || session.getPlayerTwo().equals("")) {
            session.setPlayerTwo(playerTwo);
        }
        session.setStatus(Status.CLOSE);
        return sessionRepository.save(session);
    }
}
