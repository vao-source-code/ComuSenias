package com.example.comusenias.domain.use_cases.auth

data class AuthFactoryUseCases(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val loginUseCase: LoginUseCase,
    val logoutUseCase: LogoutUseCase,
    val registerUseCase: RegisterUseCase,
    val resetPasswordUseCase: ResetPasswordUseCase
)