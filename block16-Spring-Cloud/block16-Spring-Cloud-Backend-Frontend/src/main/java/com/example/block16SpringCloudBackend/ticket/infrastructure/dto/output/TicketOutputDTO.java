package com.example.block16SpringCloudBackend.ticket.infrastructure.dto.output;

import com.example.block16SpringCloudBackend.ticket.domain.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TicketOutputDTO implements Serializable {

    private Integer idTicket;

    private Integer passengerId;

    private String passengerName;

    private String passengerLastname;

    private String passengerEmail;

    private String tripOrigin;

    private String tripDestination;

    private Date departureDate;

    private Date arrivalDate;

    private Integer idClient;

    private Integer idTrip;

    public TicketOutputDTO(Ticket ticket) {
        this.idTicket = ticket.getIdTicket();
        this.passengerId = ticket.getPassengerId();
        this.passengerName = ticket.getPassengerName();
        this.passengerLastname = ticket.getPassengerLastname();
        this.passengerEmail = ticket.getPassengerEmail();
        this.tripOrigin = ticket.getTripOrigin();
        this.tripDestination = ticket.getTripDestination();
        this.departureDate = ticket.getDepartureDate();
        this.arrivalDate = ticket.getArrivalDate();
    }
}
