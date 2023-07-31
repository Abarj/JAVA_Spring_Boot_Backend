package com.example.block17.SpringBatchChunk.application;

import com.example.block17.SpringBatchChunk.entity.Person;
import com.example.block17.SpringBatchChunk.persistance.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public Iterable<Person> saveAll(List<Person> personList) {
        return personDAO.saveAll(personList);
    }
}
