package com.example.enRaya.Backend.player.infrastructure.repository;

import com.example.enRaya.Backend.player.entity.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
    Mono<Player> findPlayerByUsername(String username);
}
