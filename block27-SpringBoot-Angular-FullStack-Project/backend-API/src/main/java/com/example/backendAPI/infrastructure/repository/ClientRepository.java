package com.example.backendAPI.infrastructure.repository;

import com.example.backendAPI.entities.Client;
import com.example.backendAPI.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select r from region r")
    List<Region> findAllRegions();
}
