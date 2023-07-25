package com.example.block16SpringCloudBackend.client.infrastructure.repository;

import com.example.block16SpringCloudBackend.client.domain.Client;
import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @NotFound
    Client findByEmail(String email);
}
