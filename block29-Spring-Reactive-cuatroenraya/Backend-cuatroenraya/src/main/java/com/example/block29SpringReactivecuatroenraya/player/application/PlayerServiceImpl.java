package com.example.block29SpringReactivecuatroenraya.player.application;

import com.example.block29SpringReactivecuatroenraya.player.entity.Player;
import com.example.block29SpringReactivecuatroenraya.player.infrastructure.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public boolean checkPlayer(Player player) {
        Mono<Player> checkPlayerMono = playerRepository.findByUsername(player.getUsername());
        Optional<Player> checkPlayerResult = checkPlayerMono.blockOptional();

        if(checkPlayerResult.isEmpty()){
            String hashedPassword = BCrypt.hashpw(player.getPassword(), BCrypt.gensalt());
            player.setPassword(hashedPassword);
            playerRepository.save(player).subscribe();
            return true;
        }

        if(BCrypt.checkpw(player.getPassword(),checkPlayerResult.get().getPassword())){
            return true;
        }

        else{
            return false;
        }
    }

    @Override
    public Mono<Player> findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }
}
