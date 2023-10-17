package com.example.comusenias.domain.use_cases.test

import com.example.comusenias.domain.repositories.UserDataStorageRepository
import javax.inject.Inject

class GetUserValue @Inject constructor(private val repository: UserDataStorageRepository) {
    suspend operator fun
            invoke(key: String) = repository.getUserValue(key)
}