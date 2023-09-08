package com.example.enRaya.Backend.notification.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "notifications")
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {

    @Id
    private UUID id;

    private String text;

    private LocalDateTime date;

    private String username;

    private String type;

    private String color;
}
