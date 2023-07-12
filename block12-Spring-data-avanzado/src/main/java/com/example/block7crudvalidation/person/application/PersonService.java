package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import org.springframework.data.domain.Page;


import java.util.Date;
import java.util.List;

public interface PersonService {
    PersonOutputDTO addPerson(PersonInputDTO personInputDto);
    PersonOutputDTO getPersonById(Integer id, String outputType);
    List<PersonOutputDTO> getPersonName(String name, String outputType);
    List<PersonOutputDTO> getPersons(String outputType);
    PersonOutputDTO updatePerson(Integer id, PersonInputDTO personInputDto);
    void deletePerson(Integer id);
    TeacherOutputDTO getTeacher(Integer id);
    List<Person> getByFields(String username, String name, String surname, Date createdDate, String orderBy, String dateCondition);
    Page<Person> getPeoplePagination (int offset, int pageSize);
}