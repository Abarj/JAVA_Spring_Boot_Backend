package com.example.block16SpringCloudBackend.client.infrastructure.dto.output;

import com.example.block16SpringCloudBackend.client.domain.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientOutputDTO {

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

    private ClientOutputDTO(ClientOutputDTO clientOutputDTO) {
    this.idClient = clientOutputDTO.getIdClient();
        this.name = clientOutputDTO.getName();
        this.surname = clientOutputDTO.getSurname();
        this.age = clientOutputDTO.getAge();
        this.email = clientOutputDTO.getEmail();
        this.phoneNumber = clientOutputDTO.getPhoneNumber();
    }
}
