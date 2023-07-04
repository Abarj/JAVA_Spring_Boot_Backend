package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.dto.input.PersonInputDTO;
import com.example.block7crudvalidation.person.infrastructure.dto.output.PersonOutputDTO;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) {
        Person person = new Person(personInputDTO);
        personRepository.save(person);

        return new PersonOutputDTO(person);
    }

    @Override
    public PersonOutputDTO getPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado ninguna persona con el id: " + id));
        return new PersonOutputDTO(person);

    }

    @Override
    public List<PersonOutputDTO> getPersonName(String username) {
        List<PersonOutputDTO> listaNombres = new ArrayList<>();
        personRepository.findByName(username).forEach(person -> {
            listaNombres.add(new PersonOutputDTO(person));
        });

        return listaNombres;
    }

    @Override
    public List<PersonOutputDTO> getPersons() {
        List<PersonOutputDTO> listaPersonas = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            PersonOutputDTO personOutputDto = new PersonOutputDTO(person);
            listaPersonas.add(personOutputDto);
        });

        return listaPersonas;
    }

    @Override
    public PersonOutputDTO updatePerson(Integer id, PersonInputDTO personInputDto) throws NotFoundException {
        Person persona = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado ningún username con el id: " + id));
        persona.update(personInputDto);
        personRepository.save(persona);

        return new PersonOutputDTO(persona);
    }

    @Override
    public void deletePerson(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado ningún username con el id: " + id));
        personRepository.delete(person);
    }
}
