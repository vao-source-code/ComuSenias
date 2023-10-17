package com.example.comusenias.domain.models.state

import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

data class RegisterState(
    val userName : String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val confirmPassword: String = EMPTY_STRING,
    val rol: String = EMPTY_STRING
)