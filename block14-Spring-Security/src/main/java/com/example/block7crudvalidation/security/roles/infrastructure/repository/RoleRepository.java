package com.example.block7crudvalidation.security.roles.infrastructure.repository;

import com.example.block7crudvalidation.security.roles.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
