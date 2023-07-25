package com.example.block16SpringCloudBackend.trip.infrastructure.dto.input;

import lombok.Data;

import java.util.Date;
@Data
public class TicketInputDTO {

    private  String origin;

    private String destination;

    private Date departureDate;

    private Date arrivalDate;

    private String  status;
}
