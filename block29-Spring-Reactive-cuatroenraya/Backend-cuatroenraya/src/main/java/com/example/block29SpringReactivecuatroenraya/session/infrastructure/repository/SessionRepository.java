package com.example.block29SpringReactivecuatroenraya.session.infrastructure.repository;

import com.example.block29SpringReactivecuatroenraya.session.entity.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


    public interface SessionRepository extends ReactiveMongoRepository<Session, String> {
}
