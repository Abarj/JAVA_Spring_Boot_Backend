package com.example.enRaya.Backend.player.application;

import com.example.enRaya.Backend.player.entity.Player;
import reactor.core.publisher.Mono;

public interface PlayerService {
    boolean playerStatus(Player player);
    Mono<Player> findPlayerByUsername(String username);
}
