package com.example.comusenias.domain.use_cases.auth

import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.repositories.AuthRepository
import javax.inject.Inject

class Register @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun  invoke(user : User) = authRepository.register(user)
}