package com.example.block13mongodb.person.application;

import com.example.block13mongodb.person.domain.Person;

import java.util.List;

public interface PersonDAL {
    Person addPerson(Person person);
    List<Person> getAllPersonPaginated(int pageNumber, int pageSize);
    Person findById(String id);
    List<Person> findByName(String name);
    Person updatePerson(String id, Person person);
    void deletePerson(Person person);
}