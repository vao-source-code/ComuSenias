package com.example.comusenias.domain.use_cases.users

data class UsersFactoryUseCases(
    val createUserUseCase: CreateUserUseCase,
    val getUserByIdUseCase: GetUserByIdUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val saveImageUserUseCase: SaveImageUserUseCase
)