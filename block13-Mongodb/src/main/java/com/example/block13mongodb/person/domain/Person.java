package com.example.block13mongodb.person.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "persons")
public class Person {

    @Id
    private String id;

    private String username;

    private String password;

    private String name;

    private int age;

    public Person() {

    }

    public Person(Person person) {
        setId(person.getId());
        setUsername(person.getUsername());
        setPassword(person.getPassword());
        setName(person.getName());
        setAge(person.getAge());
    }
}