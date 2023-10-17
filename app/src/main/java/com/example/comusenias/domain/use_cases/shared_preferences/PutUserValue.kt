package com.example.comusenias.domain.use_cases.shared_preferences

import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.repositories.UserDataStorageRepository
import javax.inject.Inject

class PutUserValue @Inject constructor(private val repository: UserDataStorageRepository) {
    suspend operator fun
            invoke(key: String, value: UserModel) =
        repository.putUserValue(key = key, value = value)
}