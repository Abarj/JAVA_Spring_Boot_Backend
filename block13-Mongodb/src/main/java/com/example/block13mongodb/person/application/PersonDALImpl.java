package com.example.block13mongodb.person.application;

import com.example.block13mongodb.person.domain.Person;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Component
public class PersonDALImpl implements PersonDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Person addPerson(Person person) {
        return mongoTemplate.save(person);
    }

    @Override
    public List<Person> getAllPersonPaginated(int pageNumber, int pageSize) {
        Query query = new Query();
        query.skip(pageNumber * pageSize);
        query.limit(pageSize);
        return mongoTemplate.find(query, Person.class);
    }

    @Override
    public Person findById(String id) {
        return mongoTemplate.findById(id, Person.class);
    }

    @Override
    public List<Person> findByName(String name) {
        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Person.class);
    }

    @Override
    public Person updatePerson(String id, Person person) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        if (person.getUsername() != null) {
            update.set("user", person.getUsername());
        }
        if (person.getPassword() != null) {
            update.set("password", person.getPassword());
        }
        if (person.getName() != null) {
            update.set("name", person.getName());
        }
        if (person.getAge() != 0) {
            update.set("age", person.getAge());
        }
        UpdateResult result = mongoTemplate.updateFirst(query, update, Person.class);
        if (result != null) {
            return mongoTemplate.findById(id, Person.class);
        } else {
            return null;
        }
    }

    @Override
    public void deletePerson(Person person) {
        mongoTemplate.remove(person);
    }
}