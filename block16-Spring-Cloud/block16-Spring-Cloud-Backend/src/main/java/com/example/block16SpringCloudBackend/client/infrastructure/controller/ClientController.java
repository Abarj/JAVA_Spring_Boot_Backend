package com.example.block16SpringCloudBackend.client.infrastructure.controller;

import com.example.block16SpringCloudBackend.client.application.ClientService;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.input.ClientInputDTO;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ClientOutputDTO addClient(@RequestBody ClientInputDTO clientInputDTO) {
        return clientService.addClient(clientInputDTO);
    }

    @GetMapping
    public List<ClientOutputDTO> findAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientOutputDTO getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ClientOutputDTO updateClient(@PathVariable Integer id, @RequestBody ClientInputDTO clientInputDTO) {
        return clientService.updateClient(id, clientInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }
}
