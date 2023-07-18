package com.example.block7crudvalidation.security.roles.infrastructure.dto;

import com.example.block7crudvalidation.security.roles.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOutputDTO {

    private String name;

    public RoleOutputDTO (Role role){
        this.name = role.getName();
    }
}
