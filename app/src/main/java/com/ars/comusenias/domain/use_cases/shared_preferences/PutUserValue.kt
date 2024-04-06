package com.ars.comusenias.domain.use_cases.shared_preferences

import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.repositories.UserDataStorageRepository
import javax.inject.Inject

class PutUserValue @Inject constructor(private val repository: UserDataStorageRepository) {
    suspend operator fun
            invoke(key: String, value: UserModel) =
        repository.putUserValue(key = key, value = value)
}