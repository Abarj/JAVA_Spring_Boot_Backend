package com.example.block7crudvalidation.security.roles.infrastructure.controller;

import com.example.block7crudvalidation.person.application.PersonService;
import com.example.block7crudvalidation.security.roles.application.RoleService;
import com.example.block7crudvalidation.security.roles.infrastructure.dto.RoleInputDTO;
import com.example.block7crudvalidation.security.roles.infrastructure.dto.RoleOutputDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/addRole")
    public String addPerson(@RequestBody RoleInputDTO roleInputDTO) throws Exception {

        roleService.addRoles(roleInputDTO);
        return "role saved";
    }

    @GetMapping("/listRole")
    public List<RoleOutputDTO> roleList() throws Exception {

        return  roleService.ShowAllRoles();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) throws Exception {
        roleService.deleteRoles(id);
        return "User deleted";
    }

    @PutMapping("/update/{id}")
    public RoleOutputDTO updateRole(@PathVariable Long id, @RequestBody RoleInputDTO roleInputDTO) throws Exception {
        return roleService.updateRole(roleInputDTO,id);
    }

    @GetMapping("/roleName/{roleName}")
    public RoleOutputDTO showByRoleName(@PathVariable("roleName") String roleName) throws Exception {
        return roleService.findByRoleName(roleName);
    }

    @PostMapping("/addRolePerson/{username}/{roleInputDto}")
    public String addRoleToPerson(@PathVariable("username") String username,@PathVariable String roleInputDto) throws Exception{
        roleService.addRolePerson(username,roleInputDto);
        return "role added";
    }

    @DeleteMapping("/deleteRolePerson/{username}/{id}")
    public String deleteRoleToPerson(@PathVariable("username") String username,@PathVariable Long id) throws Exception{
        roleService.deleteRolePerson(username,id);
        return "role deleted";

    }
    @GetMapping("/role/addtoPerson")
    public void refreshtoken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
    }





}
