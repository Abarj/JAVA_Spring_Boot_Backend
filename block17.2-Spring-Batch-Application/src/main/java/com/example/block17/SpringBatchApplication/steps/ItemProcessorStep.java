package com.example.block17.SpringBatchApplication.steps;

import com.example.block17.SpringBatchApplication.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ItemProcessorStep implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("----------> Start of PROCESS step <----------");

        // Recuperar el array<> de persons desde el contexto de ejecución mediante chunkContext
        List<Person> personList = (List<Person>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("personList");

        // Seteamos la fecha de inserción a cada persona
        List<Person> personFinalList = personList.stream().map( person -> {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            person.setInsertionDate(formatter.format(LocalDateTime.now()));
            return person;

        }).collect(Collectors.toList());

        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                // Sobreescribimos la lista de personas con los datos actualizados
                .put("personList", personFinalList);

        log.info("----------> PROCESS step completed successfully <----------");

        return RepeatStatus.FINISHED;
    }
}
