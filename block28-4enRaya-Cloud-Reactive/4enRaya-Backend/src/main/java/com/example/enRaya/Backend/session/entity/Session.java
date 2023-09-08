package com.example.enRaya.Backend.session.entity;

import com.example.enRaya.Backend.session.infrastructure.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Game")
public class Session {

    @Id
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;

    private String playerOne;

    private String playerTwo;

    private String[][] board = new String[][]{
            {"0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0"}
    };

    private Status status;
}
