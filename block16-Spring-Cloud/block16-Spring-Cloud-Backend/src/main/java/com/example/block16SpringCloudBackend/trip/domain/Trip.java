package com.example.block16SpringCloudBackend.trip.domain;

import com.example.block16SpringCloudBackend.client.domain.Client;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.input.TripInputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_trip")
    private Integer idTrip;

    private String origin;

    private Date departureDate;

    private Date arrivalDate;

    private String destination;

    private String passenger;

    private boolean status;
    @JoinTable(
            name = "rel_client_trip",
            joinColumns = @JoinColumn(name = "fk_trip", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_client", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames={"fk_trip", "fk_client"})
    )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private List<Client> passengers = new ArrayList<>();

    public Trip(TripInputDTO tripInputDTO) {
        this.origin = tripInputDTO.getOrigin();
        this.departureDate = tripInputDTO.getDepartureDate();
        this.arrivalDate = tripInputDTO.getArrivalDate();
        this.destination = tripInputDTO.getDestination();
        this.status = tripInputDTO.isStatus();

    }

    public void update(TripInputDTO tripInputDTO) {
        if (tripInputDTO.getOrigin() != null) {
            setOrigin(tripInputDTO.getOrigin());
        }
        if (tripInputDTO.getDepartureDate() != null) {
            setDepartureDate(tripInputDTO.getDepartureDate());
        }
        if (tripInputDTO.getArrivalDate() != null) {
            setArrivalDate(tripInputDTO.getArrivalDate());
        }
        if (tripInputDTO.getDestination() != null) {
            setDestination(tripInputDTO.getDestination());
        }
    }
}