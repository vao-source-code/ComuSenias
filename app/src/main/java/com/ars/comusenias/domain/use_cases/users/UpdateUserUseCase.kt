package com.ars.comusenias.domain.use_cases.users

import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.repositories.UsersRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: UserModel) = repository.updateUser(user)
}