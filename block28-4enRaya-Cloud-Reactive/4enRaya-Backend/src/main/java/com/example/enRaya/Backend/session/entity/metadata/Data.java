package com.example.enRaya.Backend.session.entity.metadata;

import java.util.UUID;

@lombok.Data
public class Data {

    public UUID sessionId;

    public String playerId;

    public Integer column;

    public Integer turn;
}
