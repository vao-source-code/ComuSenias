package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.ChildrenModel
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ChildrenRepository {
    suspend fun createUserChildren(user: ChildrenModel): Response<Boolean>
    fun getUserChildrenById(id: String): Flow<ChildrenModel>
    suspend fun updateUserChildren(user: ChildrenModel): Response<Boolean>
    suspend fun saveImageUserChildren(file: File): Response<String>
}