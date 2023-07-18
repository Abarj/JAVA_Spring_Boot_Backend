package com.example.block7crudvalidation.security.roles.application;

import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.example.block7crudvalidation.security.roles.domain.Role;
import com.example.block7crudvalidation.security.roles.infrastructure.dto.RoleInputDTO;
import com.example.block7crudvalidation.security.roles.infrastructure.dto.RoleOutputDTO;
import com.example.block7crudvalidation.security.roles.infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public String addRoles(RoleInputDTO roleInputDTO) throws Exception{
        Role role = new Role(roleInputDTO);
        roleRepository.save(role);
        return "Role saved";
    }

    @Override
    public String addRolePerson(String username, String roleName) throws EntityNotFoundException, UnprocessableEntityException {
        Person person = (Person) personRepository.findByUsername(username);
        if(username == null){
            throw  new EntityNotFoundException("username does not exist");
        }
        Role role =  roleRepository.findByName(roleName);
        if(role == null){
            throw new EntityNotFoundException("does not exist");
        }

        Collection<Role> roles = person.getRoles();
        if(roles.isEmpty()){
            person.getRoles().add(role);
            personRepository.save(person);
        }
        else if(person.getRoles().contains(role)){
            throw new UnprocessableEntityException("does not exist");
        }
        person.getRoles().add(role);
        personRepository.save(person);
        return "Role added";
    }

    @Override
    public String deleteRolePerson(String username, Long id) throws Exception {
        Person person = (Person) personRepository.findByUsername(username);
        if(username == null){
            throw  new EntityNotFoundException("username does not exist");
        }
        Role role = roleRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Does not exist"));
        if(person.getRoles().size()>0){
            person.getRoles().remove(role);
        }
        else{
            throw new UnprocessableEntityException("does not exist");
        }
        return "role deleted";
    }

    @Override
    public List<RoleOutputDTO> ShowAllRoles() {
        List<RoleOutputDTO> roleOutputDtoList = new ArrayList<>();
        roleRepository.findAll().forEach(role -> {
            RoleOutputDTO roleOutputDTO = new RoleOutputDTO(role);
            roleOutputDtoList.add(roleOutputDTO);
        });
        return roleOutputDtoList;
    }

    @Override
    public String deleteRoles(Long id) throws UnprocessableEntityException {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if(roleOptional.isEmpty()){
            throw new UnprocessableEntityException("does not exist");
        }
        roleRepository.delete(roleOptional.get());
        return "role deleted";

    }

    @Override
    public RoleOutputDTO updateRole(RoleInputDTO roleInputDTO, Long id) throws UnprocessableEntityException {
        Role role;
        Optional<Role> roleOptional = roleRepository.findById(id);
        if(roleOptional.isEmpty()){
            throw new UnprocessableEntityException("roles does not exist");
        }
        role = roleOptional.get();
        role.setName(roleInputDTO.getName());
        return new RoleOutputDTO(role);
    }

    @Override
    public RoleOutputDTO findByRoleName(String roleName) throws Exception {
        Role role= roleRepository.findByName(roleName);
        if(role == null){
            throw new EntityNotFoundException("Person does not exist");
        }
        return new RoleOutputDTO(role);
    }

    public  Person addRoleAdmin(Person person){
        RoleInputDTO roleInputDTO = new RoleInputDTO();
        roleInputDTO.setName("ADMIN");
        Role role = new Role(roleInputDTO);
        person.getRoles().add(role);
        personRepository.save(person);
        return person;
    }
    public  Person addRoleAUSER(Person person){
        RoleInputDTO roleInputDTO = new RoleInputDTO();
        roleInputDTO.setName("USER");
        Role role = new Role(roleInputDTO);
        person.getRoles().add(role);
        personRepository.save(person);
        return person;
    }


}
