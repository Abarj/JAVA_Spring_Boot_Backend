package com.example.enRaya.Backend.session.infrastructure.repository;

import com.example.enRaya.Backend.session.entity.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface SessionRepository extends ReactiveMongoRepository<Session, UUID> {
}
