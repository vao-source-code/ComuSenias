package com.example.comusenias.domain.use_cases.users

import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.repositories.UsersRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: UserModel) = repository.updateUser(user)
}