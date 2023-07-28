package com.example.block17.SpringBatchApplication.persistence;

import com.example.block17.SpringBatchApplication.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDAO extends CrudRepository<Person, Long> {
}
