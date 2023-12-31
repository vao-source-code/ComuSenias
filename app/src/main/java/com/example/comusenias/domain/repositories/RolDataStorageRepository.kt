package com.example.comusenias.domain.repositories

interface RolDataStorageRepository {
    suspend fun putRolValue(key: String, value: String)
    suspend fun getRolValue(key: String): String?
}