package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.users.UserModel

interface UserDataStorageRepository {

    suspend fun putUserValue(key: String, value: UserModel)

    suspend fun getUserValue(key: String): UserModel?
}