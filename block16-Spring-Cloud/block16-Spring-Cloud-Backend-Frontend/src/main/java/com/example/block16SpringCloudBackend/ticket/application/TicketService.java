package com.example.block16SpringCloudBackend.ticket.application;

import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.input.TicketInputDTO;
import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.output.TicketOutputDTO;

import java.util.List;

public interface TicketService {

    TicketOutputDTO addTicket(TicketInputDTO ticketInputDTO);
    List<TicketOutputDTO> getAllTickets();
    TicketOutputDTO getTicketById(Integer id);
    TicketOutputDTO updateTicket(Integer id, TicketInputDTO ticketInputDTO);
    void deleteTicket(Integer id);
}
