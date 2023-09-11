package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import kotlinx.coroutines.flow.Flow


interface UsersRepository {

    suspend fun createUser(user : User): Response<Boolean>
    fun getUserById(id: String): Flow<User>
}