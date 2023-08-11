package com.example.block22SpringAdvanced.domain.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document(collection = "app_users")
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDocument implements Serializable {

    @Id
    private String id;

    private String dni;

    private String username;

    private boolean enabled;

    private String password;

    private Role role;
}
