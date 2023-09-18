package com.example.block29SpringReactivecuatroenraya.player.infrastructure.controller;

import com.example.block29SpringReactivecuatroenraya.player.application.PlayerServiceImpl;
import com.example.block29SpringReactivecuatroenraya.player.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerServiceImpl;

    @PostMapping
    public boolean checkPlayer(@RequestBody Player player){
        return playerServiceImpl.checkPlayer(player);
    }
}