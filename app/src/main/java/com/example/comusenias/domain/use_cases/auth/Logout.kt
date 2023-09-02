package com.example.comusenias.domain.use_cases.auth

import com.example.comusenias.domain.repositories.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() {
        repository.logout()
    }
}