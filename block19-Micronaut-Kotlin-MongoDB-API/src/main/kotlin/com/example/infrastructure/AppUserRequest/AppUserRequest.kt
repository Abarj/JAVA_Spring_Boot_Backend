package com.example.infrastructure.repository.AppUserRequest

data class AppUserRequest(

    val name: String,
    val email: String,
    val street: String,
    val city: String,
    val code: Int
)
