package com.example.block17.SpringBatchApplication.steps;

import com.example.block17.SpringBatchApplication.entity.Person;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemReaderStep implements Tasklet {

    @Autowired
    // Importar archivos desde directorio Resources
    private ResourceLoader resourceLoader;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("----------> Start of READ step <----------");

        // Leer el documento definiendo un Reader
        Reader reader = new FileReader(resourceLoader.getResource("classpath:files/destination/persons.csv").getFile());

        // Declarar un objeto CSVParser para parsear la información
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .build();

        // Enviar nuestro reader en el constructor del reader builder
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .withSkipLines(1) // Inicia a partir de la segunda línea (skipeamos la primera)
                .build();

        List<Person> personList = new ArrayList<>();

        String[] actualLine;

        // Crear una persona por cada registro en persons.csv
        while((actualLine = csvReader.readNext()) != null) {
            Person person = new Person();
            person.setName(actualLine[0]);
            person.setLastName(actualLine[1]);
            person.setAge(Integer.parseInt(actualLine[2]));

            personList.add(person);
        }

        // Cerrar la conexión del Reader
        csvReader.close();
        reader.close();

        // Enviamos el Array de personas al contexto de ejecución mediante ChunkContext
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("personList", personList);

        log.info("----------> READ step completed successfully <----------");

        return RepeatStatus.FINISHED;
    }
}
