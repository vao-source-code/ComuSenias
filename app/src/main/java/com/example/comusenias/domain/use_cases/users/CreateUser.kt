package com.example.comusenias.domain.use_cases.users

import com.example.comusenias.domain.models.user.User
import com.example.comusenias.domain.repositories.UsersRepository
import javax.inject.Inject

class CreateUser @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun
            invoke(user: User) = repository.createUser(user)
}
