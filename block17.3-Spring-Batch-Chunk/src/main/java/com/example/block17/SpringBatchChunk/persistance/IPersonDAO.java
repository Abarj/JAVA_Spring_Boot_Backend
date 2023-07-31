package com.example.block17.SpringBatchChunk.persistance;

import com.example.block17.SpringBatchChunk.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDAO extends CrudRepository<Person, Long> {
}
