package com.example.block22SpringAdvanced.domain.repositories.jpa;

import com.example.block22SpringAdvanced.domain.entities.jpa.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
