package com.example.block17.SpringBatchApplication.application;

import com.example.block17.SpringBatchApplication.entity.Person;

import java.util.List;

public interface IpersonService {
    void saveAll(List<Person> personList);
}
