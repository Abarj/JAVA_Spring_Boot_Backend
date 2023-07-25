package com.example.block16SpringCloudBackend.ticket.infrastructure.controller;

import com.example.block16SpringCloudBackend.ticket.application.TicketService;
import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.input.TicketInputDTO;
import com.example.block16SpringCloudBackend.ticket.infrastructure.dto.output.TicketOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public TicketOutputDTO addTicket(@RequestBody TicketInputDTO ticketInputDTO) {
        return ticketService.addTicket(ticketInputDTO);
    }

    @GetMapping
    public List<TicketOutputDTO> findAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketOutputDTO getTicketById(@PathVariable Integer id) {
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    public TicketOutputDTO updateTicket(@PathVariable Integer id, @RequestBody TicketInputDTO ticketInputDTO) {
        return ticketService.updateTicket(id, ticketInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
}
