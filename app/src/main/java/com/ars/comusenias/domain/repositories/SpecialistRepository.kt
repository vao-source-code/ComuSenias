package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.models.users.SpecialistModel
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SpecialistRepository {
    suspend fun createUserSpecialist(user: SpecialistModel): Response<Boolean>
    fun getUserSpecialistById(id: String): Flow<SpecialistModel>
    suspend fun updateUserSpecialist(user: SpecialistModel): Response<Boolean>
    suspend fun saveImageUserSpecialist(file: File): Response<String>
    suspend fun getChildrenForSpecialistById(id: String): Flow<Response<List<ChildrenModel>>>
}