package com.ars.comusenias.domain.use_cases.auth

import com.ars.comusenias.domain.repositories.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String) = authRepository.resetPassword(email)

}