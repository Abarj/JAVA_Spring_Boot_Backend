package com.example.block22SpringAdvanced.domain.repositories.jpa;

import com.example.block22SpringAdvanced.domain.entities.jpa.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
}
