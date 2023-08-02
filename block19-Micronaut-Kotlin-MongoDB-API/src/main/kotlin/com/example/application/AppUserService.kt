package com.example.application

import com.example.entity.AppUser
import com.example.infrastructure.repository.AppUserRepository
import com.example.infrastructure.repository.AppUserRequest.SearchRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton

@Singleton
class AppUserService(
    private val appUserRepository: AppUserRepository

) {
    fun create(appUser: AppUser): AppUser =
        appUserRepository.save(appUser)

    fun getAll(): List<AppUser> =
        appUserRepository.findAll()
            .toList()

    fun getById(id: String): AppUser =
        appUserRepository.findById(id)
            .orElseThrow{ HttpStatusException(HttpStatus.NOT_FOUND, "User with id $id was not found") }

    fun update(id: String, appUser: AppUser): AppUser {
        val found = getById(id)
        val updated = appUser.copy(id = found.id)

        return appUserRepository.update(updated)
    }

    fun delete(id: String) {
        val found = getById(id)

        appUserRepository.delete(found)
    }

    fun search(searchRequest: SearchRequest): List<AppUser> =
        when {
            searchRequest.name != null -> searchByName(searchRequest.name)
            searchRequest.email != null -> searchByEmail(searchRequest.email)
            else -> emptyList()
        }

    private fun searchByEmail(email: String): List<AppUser> =
        appUserRepository.findByEmailEquals(email)

    private fun searchByName(name: String): List<AppUser> =
        appUserRepository.findByNameLike(name)
}