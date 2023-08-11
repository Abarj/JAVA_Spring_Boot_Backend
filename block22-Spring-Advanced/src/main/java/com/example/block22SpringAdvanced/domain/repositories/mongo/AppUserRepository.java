package com.example.block22SpringAdvanced.domain.repositories.mongo;

import com.example.block22SpringAdvanced.domain.entities.documents.AppUserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUserDocument, String> {

    Optional<AppUserDocument> findByUsername(String username);
}
