package com.example.comusenias.domain.use_cases.auth

import com.example.comusenias.domain.models.model.UserModel
import com.example.comusenias.domain.repositories.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun  invoke(user : UserModel) = authRepository.register(user)
}