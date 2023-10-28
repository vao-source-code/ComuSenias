package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.room.SubLevelEntity
import kotlinx.coroutines.flow.Flow

typealias SubLevels = List<SubLevelEntity>

interface SubLevelRepository {
    fun getAllSubLevel(): Flow<SubLevels>
    suspend fun getSubLevelById(idSubLevel: String): SubLevelEntity
    suspend fun insertSubLevel(subLevelEntity: List<SubLevelEntity>)
    suspend fun updateSubLevel(subLevelEntity: SubLevelEntity)
    suspend fun deleteSubLevel(subLevelEntity: SubLevelEntity)
}