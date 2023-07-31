package com.example.block17.SpringBatchChunk.steps;

import com.example.block17.SpringBatchChunk.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

@Slf4j
public class PersonItemReader extends FlatFileItemReader<Person> {

    public PersonItemReader() {
        log.info("----------> Start of READ step <----------");
        setName("readPersons");
        setResource(new ClassPathResource("persons.csv"));
        setLinesToSkip(1);
        setEncoding(StandardCharsets.UTF_8.name());
        setLineMapper(getLineMapper());
        log.info("----------> READ step completed successfully <----------");
    }

    // Tomar cada registro y convertirlo en un objeto de tipo Person
    public LineMapper<Person> getLineMapper() {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        String[] columns = new String[]{"name", "lastName", "age"};
        int[] indexFields = new int[]{0, 1, 2};

        lineTokenizer.setNames(columns);
        lineTokenizer.setIncludedFields(indexFields);
        lineTokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
