package com.example.block16SpringCloudBackend.client.infrastructure.dto.output;

import com.example.block16SpringCloudBackend.client.domain.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientOutputDTO implements Serializable {

    private Integer idClient;
    private String name;
    private String surname;
    private int age;
    private String email;
    private int phoneNumber;

    public ClientOutputDTO(Client client) {
        this.idClient = client.getIdClient();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.age = client.getAge();
        this.email = client.getEmail();
        this.phoneNumber = client.getPhoneNumber();
    }
}
