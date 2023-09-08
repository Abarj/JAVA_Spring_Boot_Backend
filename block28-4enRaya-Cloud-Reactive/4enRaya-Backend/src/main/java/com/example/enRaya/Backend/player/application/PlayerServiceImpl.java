package com.example.enRaya.Backend.player.application;

import com.example.enRaya.Backend.player.entity.Player;
import com.example.enRaya.Backend.player.infrastructure.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override // --------- Find player by username ---------
    public Mono<Player> findPlayerByUsername(String username) {
        return playerRepository.findPlayerByUsername(username);
    }

    @Override // --------- Check player status ---------
    public boolean playerStatus(Player player) {
        Mono<Player> checkPlayer = playerRepository.findPlayerByUsername(player.getUsername());
        Optional<Player> checkResponse = checkPlayer.blockOptional();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // If not player is found
        if (checkResponse.isEmpty()) { // Create a new player and save it
            // Generate random UUID
            UUID id = java.util.UUID.randomUUID();

            // Generate random password
            String passwordToHash = generateRandomPassword();
            String hashedPassword = passwordEncoder.encode(passwordToHash);

            System.out.println("Username: " + player.getUsername());
            System.out.println("Unique ID: " + id);
            System.out.println("Random password: " + passwordToHash);
            System.out.println("Password encrypted: " + hashedPassword);

            player.setPlayerId(id);
            player.setPassword(hashedPassword);
            playerRepository.save(player).subscribe();

            return true;
        }

        // If player exists and password is OK
        if (BCrypt.checkpw(player.getPassword(), checkResponse.get().getPassword())) {
            return true;
        }

        // If player exists but password is NOT OK
        else {
            return false;
        }
    }

    private static String generateRandomPassword() {
        // Generate a simple 8-character password:
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
}
