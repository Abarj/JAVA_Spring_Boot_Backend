package com.example.backendAPI.infrastructure.repository;

import com.example.backendAPI.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String term);
    List<Product> findByNameStartingWithIgnoreCase(String term);
}
