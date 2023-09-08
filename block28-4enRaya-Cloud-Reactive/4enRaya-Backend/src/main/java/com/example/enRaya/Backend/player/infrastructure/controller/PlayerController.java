package com.example.enRaya.Backend.player.infrastructure.controller;

import com.example.enRaya.Backend.player.application.PlayerServiceImpl;
import com.example.enRaya.Backend.player.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@CrossOrigin
@AllArgsConstructor
public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;

    @PostMapping
    public boolean checkPlayer(@RequestBody Player player) {
        return playerServiceImpl.playerStatus(player);
    }
}