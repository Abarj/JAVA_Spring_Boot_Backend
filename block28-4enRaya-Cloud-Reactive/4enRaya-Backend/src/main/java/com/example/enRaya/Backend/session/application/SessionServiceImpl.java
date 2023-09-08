package com.example.enRaya.Backend.session.application;

import com.example.enRaya.Backend.session.entity.Session;
import com.example.enRaya.Backend.session.infrastructure.enums.Status;
import com.example.enRaya.Backend.session.infrastructure.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override // --------- Create new game session ---------
    public Session newSession(Session session) {
        // Session ID
        UUID sessionId = UUID.randomUUID();
        // Date and time of session start
        LocalDateTime dateTimeSession = LocalDateTime.now();

        session.setId(sessionId);
        session.setCreatedAt(dateTimeSession);
        session.setStatus(Status.OPEN);
        return sessionRepository.save(session).block();

        /*
        block() -> Método que se utiliza en el contexto de programación reactiva en Java
        cuando se trabaja con reactive y con el tipo de dato Mono.
        Se utiliza para bloquear la ejecución del programa hasta que el valor dentro del Mono esté disponible
        y luego devolver ese valor. En este caso el código se bloqueará hasta que la operación de guardado
        en el repositorio se complete. Solo después de eso se continuará con la ejecución del código y dará comienzo al juego.
         */
    }

    @Override // --------- Add second player to session ---------
    public Mono<Session> addPlayerToSession(UUID sessionId, String playerTwo) {
        // Find the session
        Session session = sessionRepository.findById(sessionId).block();
        // Check that there is not a second player in that session and set the player
        if (session.getPlayerTwo() == null || session.getPlayerTwo().isEmpty()) {
            session.setPlayerTwo(playerTwo);
        }

        // Change the game status to closed
        session.setStatus(Status.CLOSE);

        return sessionRepository.save(session);
    }

    @Override // --------- Get all sessions ---------
    public Flux<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override // --------- Get session by id ---------
    public Mono<Session> getSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override // --------- Update session metadata ---------
    public void refreshSession(Session session) {
        sessionRepository.save(session).subscribe();
        /*
        subscribe() -> Se utiliza en programación reactiva para suscribirse a un flujo de datos y proporcionar
        funciones de callback que se ejecutarán cuando lleguen elementos al flujo
        o cuando se complete la secuencia.
         */
    }

    @Override // --------- Delete session ---------
    public Mono<Void> deleteSession(UUID sessionId) {
        return sessionRepository.deleteById(sessionId);
    }
}
