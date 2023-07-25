package com.example.block16SpringCloudBackend.trip.infrastructure.dto.output;

import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import com.example.block16SpringCloudBackend.trip.domain.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripOutputDTO {

    private Integer idTrip;

    private String origin;

    private String destination;

    private Date departureDate;

    private Date arrivalDate;

    private boolean status;

    private List<ClientOutputDTO> listPassengers = new ArrayList<>();

    public TripOutputDTO(Trip trip) {
        this.idTrip = trip.getIdTrip();
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();
        this.status = trip.isStatus();
    }
}