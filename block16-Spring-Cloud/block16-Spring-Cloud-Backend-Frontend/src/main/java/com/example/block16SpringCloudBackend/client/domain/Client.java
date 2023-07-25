package com.example.block16SpringCloudBackend.client.domain;

import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import com.example.block16SpringCloudBackend.trip.domain.Trip;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Client {

    private Integer idClient;

    private String name;

    private String surname;

    private int age;

    private String email;

    private int phoneNumber;

    private List<Trip> trips;

    public Client(ClientOutputDTO clientOutputDTO) {
        this.name = clientOutputDTO.getName();
        this.surname = clientOutputDTO.getSurname();
        this.age = clientOutputDTO.getAge();
        this.email = clientOutputDTO.getEmail();
        this.phoneNumber = clientOutputDTO.getPhoneNumber();
    }
}
