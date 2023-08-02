package com.example.entity

import io.micronaut.serde.annotation.Serdeable
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Serdeable.Serializable
@Deserializable
data class Address(

    val street: String,
    val city: String,
    val code: Int
)
