package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.UserModel
import kotlinx.coroutines.flow.Flow
import java.io.File


interface UsersRepository {
    suspend fun createUser(user : UserModel): Response<Boolean>
    fun getUserById(id: String): Flow<UserModel>
    suspend fun updateUser(user: UserModel): Response<Boolean>
    suspend fun saveImage(file : File): Response<String>
    
}