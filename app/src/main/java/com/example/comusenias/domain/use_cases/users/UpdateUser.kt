package com.example.comusenias.domain.use_cases.users

import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.repositories.UsersRepository
import javax.inject.Inject

class UpdateUser @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.updateUser(user)

}