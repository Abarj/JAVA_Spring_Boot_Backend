package com.example.block29SpringReactivecuatroenraya.player.infrastructure.repository;

import com.example.block29SpringReactivecuatroenraya.player.entity.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveMongoRepository<Player, Long> {

    Mono<Player> findByUsername(String username);
}
