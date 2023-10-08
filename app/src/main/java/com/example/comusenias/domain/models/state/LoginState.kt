package com.example.comusenias.domain.models.state

import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

data class LoginState(
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    )