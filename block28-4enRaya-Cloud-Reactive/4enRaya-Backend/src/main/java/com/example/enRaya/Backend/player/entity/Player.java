package com.example.enRaya.Backend.player.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("Players")
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    private UUID playerId;

    private String username;

    private String password;
}
