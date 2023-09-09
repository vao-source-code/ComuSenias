package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User


interface UsersRepository {

    suspend fun createUser(user : User): Response<Boolean>

}