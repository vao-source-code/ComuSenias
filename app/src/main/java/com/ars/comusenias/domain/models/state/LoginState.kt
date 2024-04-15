package com.ars.comusenias.domain.models.state

import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING

data class LoginState(
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    )