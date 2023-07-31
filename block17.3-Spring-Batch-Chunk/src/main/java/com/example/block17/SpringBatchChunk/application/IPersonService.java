package com.example.block17.SpringBatchChunk.application;

import com.example.block17.SpringBatchChunk.entity.Person;

import java.util.List;

public interface IPersonService {
    Iterable<Person> saveAll(List<Person> personList);
}
