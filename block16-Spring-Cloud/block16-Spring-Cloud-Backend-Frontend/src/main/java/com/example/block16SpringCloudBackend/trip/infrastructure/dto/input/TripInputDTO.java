package com.example.block16SpringCloudBackend.entities.trip.infrastructure.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripInputDTO {

    private String origin;

    private Date departureDate;

    private Date arrivalDate;

    private String destination;

    private String passenger;

    private boolean status;
}
