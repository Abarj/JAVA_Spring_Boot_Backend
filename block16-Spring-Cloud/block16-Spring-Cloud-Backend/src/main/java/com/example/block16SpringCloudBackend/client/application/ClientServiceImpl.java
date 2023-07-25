package com.example.block16SpringCloudBackend.client.application;

import com.example.block16SpringCloudBackend.client.domain.Client;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.input.ClientInputDTO;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import com.example.block16SpringCloudBackend.client.infrastructure.repository.ClientRepository;
import com.example.block16SpringCloudBackend.utils.exceptions.CustomUnprocessableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientOutputDTO addClient(ClientInputDTO clientInputDTO) {
        if (clientRepository.findByEmail(clientInputDTO.getEmail()) == null) {
            Client client = new Client(clientInputDTO);
            clientRepository.save(client);

            return new ClientOutputDTO(client);
        }

        throw new CustomUnprocessableException(
                "The person with email " + clientInputDTO.getEmail() + " already exists.");
    }

    @Override
    public List<ClientOutputDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientOutputDTO> clientOutputDTOS = new ArrayList<>();

        for (Client client : clients) {
            clientOutputDTOS.add(new ClientOutputDTO(client));
        }

        return clientOutputDTOS;
    }

    @Override
    public ClientOutputDTO getClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún cliente con el id: " + id));

        return new ClientOutputDTO(client);
    }

    @Override
    public ClientOutputDTO updateClient(Integer id, ClientInputDTO clientInputDTO) {

        if (clientRepository.findByEmail(clientInputDTO.getEmail()) == null) {
            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new CustomUnprocessableException("No se ha encontrado ningún cliente con el id: " + id));
            client.update(clientInputDTO);
            clientRepository.save(client);

            return new ClientOutputDTO(client);
        }

        throw new CustomUnprocessableException(
                "The person with email " + clientInputDTO.getEmail() + " already exists.");

    }

    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
