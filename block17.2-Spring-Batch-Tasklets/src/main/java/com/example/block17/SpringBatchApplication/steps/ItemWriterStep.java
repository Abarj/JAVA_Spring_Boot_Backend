package com.example.block17.SpringBatchApplication.steps;

import com.example.block17.SpringBatchApplication.application.IpersonService;
import com.example.block17.SpringBatchApplication.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class ItemWriterStep implements Tasklet {

    @Autowired
    private IpersonService ipersonService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("----------> Start of WRITER step <----------");

        // Recuperar el array<> de persons desde el contexto de ejecución mediante chunkContext
        List<Person> personList = (List<Person>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("personList");

        personList.forEach( person -> {
            if(person != null) {
                log.info(person.toString());
            }
        });

        ipersonService.saveAll(personList);

        log.info("----------> WRITER step completed successfully <----------");

        return null;
    }
}
