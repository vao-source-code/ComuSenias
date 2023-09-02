package com.example.comusenias.domain.use_cases.auth

import com.example.comusenias.domain.repositories.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepository.login(email, password)

}