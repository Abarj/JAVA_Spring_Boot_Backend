package com.example.block17.SpringBatchChunk.steps;

import com.example.block17.SpringBatchChunk.application.IPersonService;
import com.example.block17.SpringBatchChunk.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class PersonItemWriter implements ItemWriter<Person> {

    @Autowired
    private IPersonService personService;

    @Override
    public void write(List<? extends Person> items) {
        log.info("----------> Start of WRITER step <----------");
        items.forEach(person -> log.info(person.toString()));
        personService.saveAll((List<Person>) items);
        log.info("----------> WRITER step completed successfully <----------");
    }
}
