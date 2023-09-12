package com.example.backendAPI.infrastructure.controller;

import com.example.backendAPI.application.ClientService;
import com.example.backendAPI.entities.Bill;
import com.example.backendAPI.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api")
public class BillController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/bills/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bill show(@PathVariable Long id) {
        return clientService.findBillById(id);
    }

    @DeleteMapping("/bills/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.deteleBillById(id);
    }

    @GetMapping("/bills/filter-products/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterProducts(@PathVariable String term) {
        return clientService.findProductByName(term);
    }

    @PostMapping("/bills")
    @ResponseStatus(HttpStatus.CREATED)
    public Bill create(@RequestBody Bill bill) {
        return clientService.saveBill(bill);
    }
}
