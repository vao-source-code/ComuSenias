package com.example.comusenias.domain.use_cases.users

data class UsersUseCase(
    val createUser: CreateUser,
    val getUserById: GetUserById,
    val updateUser: UpdateUser,
    val saveImageUser: SaveImageUser
)