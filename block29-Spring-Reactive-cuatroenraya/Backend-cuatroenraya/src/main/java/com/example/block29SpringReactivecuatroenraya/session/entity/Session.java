package com.example.block29SpringReactivecuatroenraya.session.entity;

import com.example.block29SpringReactivecuatroenraya.session.infrastructure.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("session")
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    @Id
    private String id;

    private String createdAt;

    private String playerOne;

    private String playerTwo;

    private Status status;

    private String[][] board = new String[][]{
        {"0","0","0","0","0","0","0"},
        {"0","0","0","0","0","0","0"},
        {"0","0","0","0","0","0","0"},
        {"0","0","0","0","0","0","0"},
        {"0","0","0","0","0","0","0"},
        {"0","0","0","0","0","0","0"}
    };
}
