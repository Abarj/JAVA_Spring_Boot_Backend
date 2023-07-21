package com.example.block7crudvalidation.security.role.repository;

import com.example.block7crudvalidation.security.role.domain.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
}
