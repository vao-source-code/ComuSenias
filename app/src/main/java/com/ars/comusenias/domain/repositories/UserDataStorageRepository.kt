package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.users.UserModel

interface UserDataStorageRepository {
    suspend fun putUserValue(key: String, value: UserModel)
    suspend fun getUserValue(key: String): UserModel?
}