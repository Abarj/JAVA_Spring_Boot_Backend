package com.example.block29SpringReactivecuatroenraya.session.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata {

    private int sessionId;

    private String playerId;

    private Integer column;

    private Integer round;
}
