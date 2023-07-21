package com.example.block7crud.application;

import org.springframework.stereotype.Service;
import com.example.block7crud.domain.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    private List<Persona> personList = new ArrayList<>();
    private static Long lastId = 0L;

    private Long generateUniqueId() {
        // Incrementar el último ID y devolverlo
        return ++lastId;
    }

    @Override
    public Iterable<Persona> findAll() {
        return personList;
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personList.stream().filter(person -> Objects.equals(person.getId(), id)).findFirst();
    }

    @Override
    public Optional<Persona> findByName(String nombre) {
        return personList.stream().filter(person -> Objects.equals(person.getNombre(), nombre)).findFirst();
    }

    @Override
    public Persona create(Persona newPerson) {
        // Generar un nuevo ID único
        long newId = generateUniqueId();

        // Asignar el ID a la persona
        newPerson.setId((int) newId);

        // Agregar la persona a la lista
        personList.add(newPerson);

        return newPerson;
    }

    @Override
    public Optional<Persona> update(Long id, Persona newPerson) {
        Optional<Persona> person = personList.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
        if (person.isEmpty()) return Optional.empty();

        int personIndex = personList.indexOf(person.get());
        personList.set(personIndex, newPerson);

        return Optional.ofNullable(newPerson);
    }

    @Override
    public String delete(Long id) {
        Optional<Persona> person = personList.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
        if (person.isEmpty()) return "Persona no encontrada";

        personList.removeIf(p -> Objects.equals(p.getId(), id));

        return "La persona con ID: " + id + " ha sido eliminada con éxito";
    }
}
