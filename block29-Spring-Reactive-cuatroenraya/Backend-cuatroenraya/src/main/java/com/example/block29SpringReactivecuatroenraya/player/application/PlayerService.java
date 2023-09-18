package com.example.block29SpringReactivecuatroenraya.player.application;

import com.example.block29SpringReactivecuatroenraya.player.entity.Player;
import reactor.core.publisher.Mono;

public interface PlayerService {

    boolean checkPlayer(Player player);
    Mono<Player> findByUsername(String username);
}
