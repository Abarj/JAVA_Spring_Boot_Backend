package com.example.block16SpringCloudBackend.ticket.domain;

import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.input.TicketInputDTO;
import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.output.TicketOutputDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ticket")
    private Integer idTicket;

    private Integer passengerId;

    private String passengerName;

    private String passengerLastname;

    private String passengerEmail;

    private String tripOrigin;

    private String tripDestination;

    private Date departureDate;

    private Date arrivalDate;

    public void update(TicketInputDTO ticketInputDTO) {
        if (ticketInputDTO.getPassengerId() != null) {
            setPassengerId(ticketInputDTO.getPassengerId());
        }
        if (ticketInputDTO.getPassengerName() != null) {
            setPassengerName(ticketInputDTO.getPassengerName());
        }
        if (ticketInputDTO.getPassengerLastname() != null) {
            setPassengerLastname(ticketInputDTO.getPassengerLastname());
        }
        if (ticketInputDTO.getPassengerEmail() != null) {
            setPassengerEmail(ticketInputDTO.getPassengerEmail());
        }
        if (ticketInputDTO.getTripOrigin() != null) {
            setTripOrigin(ticketInputDTO.getTripOrigin());
        }
        if (ticketInputDTO.getTripDestination() != null) {
            setTripDestination(ticketInputDTO.getTripDestination());
        }
        if (ticketInputDTO.getDepartureDate() != null) {
            setDepartureDate(ticketInputDTO.getDepartureDate());
        }
        if (ticketInputDTO.getArrivalDate() != null) {
            setArrivalDate(ticketInputDTO.getArrivalDate());
        }
    }

    public Ticket(TicketInputDTO ticketInputDTO) {
        this.passengerId = ticketInputDTO.getPassengerId();
        this.passengerName = ticketInputDTO.getPassengerName();
        this.passengerLastname = ticketInputDTO.getPassengerLastname();
        this.passengerEmail = ticketInputDTO.getPassengerEmail();
        this.tripOrigin = ticketInputDTO.getTripOrigin();
        this.tripDestination = ticketInputDTO.getTripDestination();
        this.departureDate = ticketInputDTO.getDepartureDate();
        this.arrivalDate = ticketInputDTO.getArrivalDate();
    }
}