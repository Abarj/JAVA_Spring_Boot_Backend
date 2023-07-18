package com.example.block7crudvalidation.security.roles.application;

import com.example.block7crudvalidation.security.roles.infrastructure.dto.RoleInputDTO;
import com.example.block7crudvalidation.security.roles.infrastructure.dto.RoleOutputDTO;

import java.util.List;

public interface RoleService {
    public String addRoles(RoleInputDTO roleInputDTO) throws Exception;
    public String addRolePerson(String username,String roleName) throws  Exception;
    public List<RoleOutputDTO> ShowAllRoles() throws Exception;
    public String deleteRoles(Long id) throws Exception;
    public RoleOutputDTO updateRole(RoleInputDTO roleInputDto, Long id) throws Exception;
    public RoleOutputDTO findByRoleName(String roleName)throws Exception;
    public String deleteRolePerson(String username, Long  id) throws  Exception;
}
