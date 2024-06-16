package com.ars.comusenias.domain.models.auth

import com.ars.comusenias.domain.use_cases.auth.GetCurrentUserUseCase
import com.ars.comusenias.domain.use_cases.auth.LoginUseCase
import com.ars.comusenias.domain.use_cases.auth.LogoutUseCase
import com.ars.comusenias.domain.use_cases.auth.RegisterUseCase

data class AuthFactory(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val loginUseCase: LoginUseCase,
    val logoutUseCase: LogoutUseCase,
    val registerUseCase: RegisterUseCase
)