package com.example.block16SpringCloudBackend.client.domain;

import com.example.block16SpringCloudBackend.client.infrastructure.dto.input.ClientInputDTO;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import com.example.block16SpringCloudBackend.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private Integer idClient;

    private String name;

    private String surname;

    private int age;

    private String email;

    private int phoneNumber;

    @ManyToMany(mappedBy = "passengers", fetch = FetchType.LAZY)
    private List<Trip> tripList;

    public Client(ClientInputDTO clientInputDTO) {
        this.name = clientInputDTO.getName();
        this.surname = clientInputDTO.getSurname();
        this.age = clientInputDTO.getAge();
        this.email = clientInputDTO.getEmail();
        this.phoneNumber = clientInputDTO.getPhoneNumber();
    }

    public void update(ClientInputDTO clientInputDTO) {
        if (clientInputDTO.getName() != null) {
            setName(clientInputDTO.getName());
        }
        if (clientInputDTO.getSurname() != null) {
            setSurname(clientInputDTO.getSurname());
        }
        setAge(clientInputDTO.getAge());
        if (clientInputDTO.getEmail() != null) {
            setEmail(clientInputDTO.getEmail());
        }
        setPhoneNumber(clientInputDTO.getPhoneNumber());
    }
}
