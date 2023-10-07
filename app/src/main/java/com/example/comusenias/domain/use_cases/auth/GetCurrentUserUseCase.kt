package com.example.comusenias.domain.use_cases.auth

import com.example.comusenias.domain.repositories.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.currentUser
}