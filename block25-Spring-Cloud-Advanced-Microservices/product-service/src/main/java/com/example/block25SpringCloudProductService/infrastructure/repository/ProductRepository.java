package com.example.block25SpringCloudProductService.infrastructure.repository;

import com.example.block25SpringCloudProductService.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
