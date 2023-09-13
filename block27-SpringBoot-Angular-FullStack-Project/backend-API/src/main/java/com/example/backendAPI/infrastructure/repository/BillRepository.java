package com.example.backendAPI.infrastructure.repository;

import com.example.backendAPI.entities.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
