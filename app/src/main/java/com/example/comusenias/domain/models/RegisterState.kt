package com.example.comusenias.domain.models

data class RegisterState(
    val userName : String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)