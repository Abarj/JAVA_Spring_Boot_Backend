package com.example.block16SpringCloudBackend.trip.domain;

import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.output.TripOutputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idTrip;

    private String origin;

    private Date departureDate;

    private Date arrivalDate;

    private String destination;

    private boolean status;

    private String passenger;

    private List<ClientOutputDTO> passengers = new ArrayList<>();

    public Trip(TripOutputDTO tripOutputDTO) {
        this.origin = tripOutputDTO.getOrigin();
        this.departureDate = tripOutputDTO.getDepartureDate();
        this.arrivalDate = tripOutputDTO.getArrivalDate();
        this.destination = tripOutputDTO.getDestination();
        this.status = tripOutputDTO.isStatus();
        this.passengers = tripOutputDTO.getListPassengers();
    }
}
