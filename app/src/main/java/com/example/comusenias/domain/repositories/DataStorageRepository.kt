package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.users.UserModel


interface DataStorageRepository {
    suspend fun putValueUser(key: String, value: String)
    suspend fun getValueUser(key: String): UserModel?
    suspend fun putValueRole(key: String, value: String)
    suspend fun getValueRole(key: String): String?
}