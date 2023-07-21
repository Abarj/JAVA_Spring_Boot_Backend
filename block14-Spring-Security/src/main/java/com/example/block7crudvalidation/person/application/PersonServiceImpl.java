package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.domain.DateParameters;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonStudentDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonTeacherDTO;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infrastructure.dto.output.StudentOutputDTO;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infrastructure.dto.output.TeacherOutputDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) {
        Person person = new Person(personInputDTO);
        personRepository.save(person);

        return new PersonOutputDTO(person);
    }

    public List<PersonOutputDTO> getPersons(String outputType) {
        List<PersonOutputDTO> listaPersonas = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            if ("full".equals(outputType)) {
                if (person.getStudent() != null) {
                    Student student = person.getStudent();
                    PersonStudentDTO dto = new PersonStudentDTO(person);
                    dto.setStudent(new StudentOutputDTO(student));
                    listaPersonas.add(dto);
                } else if (person.getTeacher() != null) {
                    Teacher teacher = person.getTeacher();
                    PersonTeacherDTO dto = new PersonTeacherDTO(person);
                    dto.setTeacher(new TeacherOutputDTO(teacher));
                    listaPersonas.add(dto);
                }
            } else {
                listaPersonas.add(new PersonOutputDTO(person));
            }
        });

        return listaPersonas;
    }

    @Override
    public PersonOutputDTO getPersonById(Integer id, String outputType) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna persona con el id: " + id));


        if ("full".equals(outputType)) {
            if (person.getStudent() != null) {
                Student student = person.getStudent();
                PersonStudentDTO dto = new PersonStudentDTO(person);
                dto.setStudent(new StudentOutputDTO(student));
                return dto;
            } else if (person.getTeacher() != null) {
                Teacher teacher = person.getTeacher();
                PersonTeacherDTO dto = new PersonTeacherDTO(person);
                dto.setTeacher(new TeacherOutputDTO(teacher));
                return dto;
            }
        }
        return new PersonOutputDTO(person);
    }

    @Override
    public List<PersonOutputDTO> getPersonName(String name, String outputType) {
        List<PersonOutputDTO> listaNombres = new ArrayList<>();
        personRepository.findByName(name).forEach(person -> {
            if ("full".equals(outputType)) {
                if (person.getStudent() != null) {
                    Student student = person.getStudent();
                    PersonStudentDTO dto = new PersonStudentDTO(person);
                    dto.setStudent(new StudentOutputDTO(student));
                    listaNombres.add(dto);
                } else if (person.getTeacher() != null) {
                    Teacher teacher = person.getTeacher();
                    PersonTeacherDTO dto = new PersonTeacherDTO(person);
                    dto.setTeacher(new TeacherOutputDTO(teacher));
                    listaNombres.add(dto);
                }
            } else {
                listaNombres.add(new PersonOutputDTO(person));
            }
        });

        return listaNombres;
    }

    @Override
    public List<PersonOutputDTO> getPersonUsername(String username, String outputType) {
        List<PersonOutputDTO> listaNombres = new ArrayList<>();
        personRepository.findByUsername(username).forEach(person -> {
            if ("full".equals(outputType)) {
                if (person.getStudent() != null) {
                    Student student = person.getStudent();
                    PersonStudentDTO dto = new PersonStudentDTO(person);
                    dto.setStudent(new StudentOutputDTO(student));
                    listaNombres.add(dto);
                } else if (person.getTeacher() != null) {
                    Teacher teacher = person.getTeacher();
                    PersonTeacherDTO dto = new PersonTeacherDTO(person);
                    dto.setTeacher(new TeacherOutputDTO(teacher));
                    listaNombres.add(dto);
                }
            } else {
                listaNombres.add(new PersonOutputDTO(person));
            }
        });

        return listaNombres;
    }



    @Override
    public PersonOutputDTO updatePerson(Integer id, PersonInputDTO personInputDto) throws RuntimeException {
        Person persona = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún username con el id: " + id));
        persona.update(personInputDto);
        personRepository.save(persona);

        return new PersonOutputDTO(persona);
    }

    @Override
    public void deletePerson(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún username con el id: " + id));
        personRepository.delete(person);
    }

    @Override
    public TeacherOutputDTO getTeacher(Integer id) {
        String url = "http://localhost:8081/teacher/" + id;

        try{
            ResponseEntity<TeacherOutputDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, TeacherOutputDTO.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return new TeacherOutputDTO();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> getByFields(String usuario, String name, String surname, Date createdDate, String orderBy, String dateParameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();
        if(usuario != null) {
            predicates.add(criteriaBuilder.like(root.get("username"), usuario));
        }

        if(name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), name));
        }

        if(surname != null) {
            predicates.add(criteriaBuilder.like(root.get("surname"), surname));
        }

        if(createdDate != null) {

            switch (dateParameters){
                case DateParameters.EQUAL -> predicates.add(criteriaBuilder.equal(root.get("createdDate"), createdDate));
                case DateParameters.LESS_THAN -> predicates.add(criteriaBuilder.lessThan(root.get("createdDate"), createdDate));
                default -> predicates.add(criteriaBuilder.greaterThan(root.get("createdDate"), createdDate));
            }
        }

        query.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));

        if(Objects.equals(orderBy, "username")) {
            query.orderBy(criteriaBuilder.asc(root.get("username")));
        }
        if(Objects.equals(orderBy, "name")) {
            query.orderBy(criteriaBuilder.asc(root.get("name")));
        }

        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .toList();
    }

    @Override
    public Page<Person> getPeoplePagination(int offset, int pageSize) {
        return personRepository.findAll(PageRequest.of(offset, pageSize));    }
}
