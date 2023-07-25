package com.example.block16SpringCloudBackend.client.application;

import com.example.block16SpringCloudBackend.client.infrastructure.dto.input.ClientInputDTO;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    ClientOutputDTO addClient(ClientInputDTO clientInputDTO);
    List<ClientOutputDTO> getAllClients();
    ClientOutputDTO getClientById(Integer id);
    ClientOutputDTO updateClient(Integer id, ClientInputDTO clientInputDTO);
    void deleteClient(Integer id);
}
