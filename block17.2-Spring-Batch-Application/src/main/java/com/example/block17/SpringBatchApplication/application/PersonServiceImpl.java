package com.example.block17.SpringBatchApplication.application;

import com.example.block17.SpringBatchApplication.entity.Person;
import com.example.block17.SpringBatchApplication.persistence.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements IpersonService {

    @Autowired
    private IPersonDAO personDAO;

    @Override
    @Transactional
    public void saveAll(List<Person> personList) {

        personDAO.saveAll(personList);
    }
}
