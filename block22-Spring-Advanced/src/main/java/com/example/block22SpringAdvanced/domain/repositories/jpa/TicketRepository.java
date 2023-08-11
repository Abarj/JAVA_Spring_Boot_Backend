package com.example.block22SpringAdvanced.domain.repositories.jpa;

import com.example.block22SpringAdvanced.domain.entities.jpa.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {
}
