package com.example.comusenias.domain.use_cases.shared_preferences


import com.example.comusenias.domain.repositories.RolDataStorageRepository
import javax.inject.Inject


class PutRolValue @Inject constructor(private val repository: RolDataStorageRepository) {
    suspend operator fun
            invoke(key: String, value: String) =
        repository.putRolValue(key = key, value = value)
}