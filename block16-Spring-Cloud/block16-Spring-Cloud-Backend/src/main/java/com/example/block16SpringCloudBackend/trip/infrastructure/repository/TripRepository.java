package com.example.block16SpringCloudBackend.trip.infrastructure.repository;

import com.example.block16SpringCloudBackend.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

}
