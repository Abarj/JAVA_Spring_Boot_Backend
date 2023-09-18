package com.example.block29SpringReactivecuatroenraya.player.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("players")
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    private String id;

    private String username;

    private String password;
}
