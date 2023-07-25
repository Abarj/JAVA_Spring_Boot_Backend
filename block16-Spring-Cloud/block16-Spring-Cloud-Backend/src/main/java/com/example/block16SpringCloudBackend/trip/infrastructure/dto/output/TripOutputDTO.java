package com.example.block16SpringCloudBackend.trip.infrastructure.dto.output;

import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import com.example.block16SpringCloudBackend.trip.domain.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class TripOutputDTO implements Serializable {

    private Integer idTrip;
    private String origin;
    private Date departureDate;
    private Date arrivalDate;
    private String destination;
    private List<ClientOutputDTO> passengers = new ArrayList<>();
    private boolean status;

    public TripOutputDTO(Trip trip) {
        this.idTrip = trip.getIdTrip();
        this.origin = trip.getOrigin();
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();
        this.destination = trip.getDestination();
        this.passengers = trip.getPassengers().stream().map(ClientOutputDTO::new).toList();
        this.status = trip.isStatus();

    }
}
