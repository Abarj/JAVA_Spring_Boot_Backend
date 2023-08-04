package com.example.block20SpringBootKeycloadkOAuth20.application;

import com.example.block20SpringBootKeycloadkOAuth20.infrastructure.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeycloakService {
    String createUser(UserDTO userDTO);
    List<UserRepresentation> findAllUsers(); //UserRepresentation es el tipo de datos que representa a los usuarios en Keycloak
    List<UserRepresentation> searchUserByUsername(String username);
    void updateUser(String userId, UserDTO userDTO);
    void deleteUser(String userId);
}
