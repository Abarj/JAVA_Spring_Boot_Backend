package com.example.backendAPI.application;

import com.example.backendAPI.entities.Bill;
import com.example.backendAPI.entities.Client;
import com.example.backendAPI.entities.Product;
import com.example.backendAPI.entities.Region;
import com.example.backendAPI.infrastructure.repository.BillRepository;
import com.example.backendAPI.infrastructure.repository.ClientRepository;
import com.example.backendAPI.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegions() {
        return clientRepository.findAllRegions();
    }

    @Override
    @Transactional(readOnly = true)
    public Bill findBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    @Transactional
    public void deteleBillById(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Product> findProductByName(String term) {
        return productRepository.findByNameContainingIgnoreCase(term);
    }
}
