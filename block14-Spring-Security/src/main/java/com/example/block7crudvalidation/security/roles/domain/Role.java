package com.example.block7crudvalidation.security.roles.domain;

import com.example.block7crudvalidation.security.roles.infrastructure.dto.RoleInputDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Role(RoleInputDTO roleInputDTO){
        this.name = roleInputDTO.getName();
    }
}