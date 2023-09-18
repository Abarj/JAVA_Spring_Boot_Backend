package com.example.block29SpringReactivecuatroenraya.session.application;

import com.example.block29SpringReactivecuatroenraya.session.entity.Session;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SessionService {

    Session newSession(Session session);
    void updateSession(Session session);
    /*
    Mono -> Forma parte de Spring Reactive como biblioteca de programación reactiva.
    Es una clase que representa una fuente de un solo valor o un valor nulo (similar a Optional)
    Puede ser usado para representar resultados de operaciones asíncronas, como solicitudes HTTP
    o acceso a bases de datos, que devuelven un solo resultado.
     */
    Mono<Session> getSessionById(String sessionId);
    /*
    Flux -> Forma parte de Spring Reactive como biblioteca de programación reactiva.
    Es una clase que representa una secuencia de cero o más valores (similar a Stream)
    Puede emitir múltiples valores de forma asincrónica en el tiempo y es útil para
    representar secuencias infinitas o finitas de eventos, como flujos de datos en tiempo real.
     */
    Flux<Session> getAllSessions();
    Mono<Void> deleteSession (String sessionId);
    Mono<Session> addPlayerTwo(String sessionId, String playerTwo);
}
