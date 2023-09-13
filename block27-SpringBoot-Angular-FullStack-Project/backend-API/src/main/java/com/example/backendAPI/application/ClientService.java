package com.example.backendAPI.application;

import com.example.backendAPI.entities.Bill;
import com.example.backendAPI.entities.Client;
import com.example.backendAPI.entities.Product;
import com.example.backendAPI.entities.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);
    List<Client> findAll();
    Page<Client> findAll(Pageable pageable);
    Client findClientById(Long id);
    void delete(Long id);
    List<Region> findAllRegions();
    Bill findBillById(Long id);
    Bill saveBill(Bill bill);
    void deteleBillById(Long id);
    List<Product> findProductByName(String term);
}
