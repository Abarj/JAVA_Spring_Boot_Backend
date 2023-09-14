package com.example.block28SpringBootWebSocketRealTimeChat.entities.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "messages")
public class Message implements Serializable {

    @Id
    private String id;

    private String text;

    private Long date;

    private String username;

    private String type;

    private String color;
}
