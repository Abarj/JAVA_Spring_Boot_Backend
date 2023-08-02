package com.example.infrastructure.repository

import com.example.entity.AppUser
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface AppUserRepository : CrudRepository<AppUser, String> {

    fun findByEmailEquals(email: String): List<AppUser>

    @MongoFindQuery("{ name: { \$regex: :name, \$options: 'i' }}")
    fun findByNameLike(name: String): List<AppUser>
}