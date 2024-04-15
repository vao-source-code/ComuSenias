package com.ars.comusenias.domain.use_cases.auth

import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.repositories.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun  invoke(user : UserModel) = authRepository.register(user)
}