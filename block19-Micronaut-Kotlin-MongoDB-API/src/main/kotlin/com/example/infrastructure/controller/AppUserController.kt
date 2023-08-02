package com.example.infrastructure.controller

import com.example.application.AppUserService
import com.example.entity.Address
import com.example.entity.AppUser
import com.example.infrastructure.repository.AppUserRequest.AppUserRequest
import com.example.infrastructure.repository.AppUserRequest.SearchRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*

@Controller("/user")
class AppUserController(
    private val appUserService : AppUserService

) {
    @Post
    @Status(HttpStatus.CREATED)
    fun create(appUserRequest: AppUserRequest) =
        appUserService.create(
            appUser = appUserRequest.toModel()
        )

    @Get
    fun getAll() =
        appUserService.getAll()

    @Get("/{id}")
    fun getById(id: String) =
        appUserService.getById(id)

    @Put("/{id}")
    fun update(
        id: String,
        request: AppUserRequest,
        @Header("Update") header: String
    ): AppUser {
        println("Header: $header")

        return appUserService.update(id, request.toModel())
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun delete(id: String) {
        appUserService.delete(id)
    }

    @Post("/search")
    fun search(searchRequest: SearchRequest): List<AppUser> =
        appUserService.search(searchRequest)

    private fun AppUserRequest.toModel() : AppUser =
        AppUser(
            name = this.name,
            email = this.email,
            address = Address(
                street = this.street,
                city = this.city,
                code = this.code
            )
        )
}