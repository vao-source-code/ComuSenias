package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.ChildrenModel
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ChildrenRepository {
    suspend fun createUserChildren(user: ChildrenModel): Response<Boolean>
    fun getUserChildrenById(id: String): Flow<ChildrenModel>
    suspend fun updateUserChildren(user: ChildrenModel): Response<Boolean>
    suspend fun saveImageUserChildren(file: File): Response<String>
    suspend fun integrateChildrenWithSpecialist(user: ChildrenModel): Response<Boolean>
    suspend fun updateLevelChildren(user: ChildrenModel): Response<Boolean>
}