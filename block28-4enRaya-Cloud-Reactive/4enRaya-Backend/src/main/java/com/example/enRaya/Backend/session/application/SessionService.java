package com.example.enRaya.Backend.session.application;

import com.example.enRaya.Backend.session.entity.Session;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SessionService {

    // --------- Create new game session ---------
    public Session newSession(Session session);

    /*
    Mono -> Clase que representa una fuente de un solo valor o un valor nulo (similar a Optional)
    En este caso lo utilizaremos para representar resultados de operaciones asíncronas,
    como solicitudes HTTP o acceso a bases de datos, que devuelven un solo resultado.
     */
    // --------- Add second player to session ---------
    public Mono<Session> addPlayerToSession(UUID sessionId, String playerTwo);

    /*
    Flux ->  Clase que representa una secuencia de cero o más valores (similar a Stream)
    Es útil para representar secuencias infinitas o finitas de eventos, como flujos de datos en tiempo real.
    Se puede usar para manejar flujos de eventos, como streams de entrada/salida, eventos de usuario, etc.
     */
    // --------- Get all sessions ---------
    public Flux<Session> getAllSessions();

    // --------- Get session by id ---------
    public Mono<Session> getSessionById(UUID sessionId);

    // --------- Update session metadata ---------
    void refreshSession(Session session);

    // --------- Delete session ---------
    public Mono<Void> deleteSession(UUID sessionId);
}
