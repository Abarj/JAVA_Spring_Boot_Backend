package com.example.block22SpringAdvanced.domain.repositories.jpa;

import com.example.block22SpringAdvanced.domain.entities.jpa.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}
