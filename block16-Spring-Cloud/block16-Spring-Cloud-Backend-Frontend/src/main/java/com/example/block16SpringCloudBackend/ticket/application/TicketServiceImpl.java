package com.example.block16SpringCloudBackend.ticket.application;

import com.example.block16SpringCloudBackend.ticket.domain.Ticket;
import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.input.TicketInputDTO;
import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.output.TicketOutputDTO;
import com.example.block16SpringCloudBackend.ticket.infrastructure.repository.TicketRepository;
import com.example.block16SpringCloudBackend.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketOutputDTO addTicket(TicketInputDTO ticketInputDTO) {
        Ticket ticket = new Ticket(ticketInputDTO);
        ticketRepository.save(ticket);

        return new TicketOutputDTO(ticket);
    }

    @Override
    public List<TicketOutputDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketOutputDTO> ticketOutputDTOS = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ticketOutputDTOS.add(new TicketOutputDTO(ticket));
        }

        return ticketOutputDTOS;
    }

    @Override
    public TicketOutputDTO getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún ticket con el id: " + id));

        return new TicketOutputDTO(ticket);
    }

    @Override
    public TicketOutputDTO updateTicket(Integer id, TicketInputDTO ticketInputDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún ticket con el id: " + id));
        ticket.update(ticketInputDTO);
        ticketRepository.save(ticket);

        return new TicketOutputDTO(ticket);
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
}