package com.example.block16SpringCloudBackend.ticket.infrastructure.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TicketInputDTO implements Serializable {

    private Integer passengerId;

    private String passengerName;

    private String passengerLastname;

    private String passengerEmail;

    private String tripOrigin;

    private String tripDestination;

    private Date departureDate;

    private Date arrivalDate;
}
