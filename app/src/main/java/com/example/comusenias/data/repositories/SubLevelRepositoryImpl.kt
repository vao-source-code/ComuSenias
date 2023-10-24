package com.example.comusenias.data.repositories

import com.example.comusenias.data.repositories.room.SubLevelDao
import com.example.comusenias.domain.models.room.SubLevelEntity
import com.example.comusenias.domain.repositories.SubLevelRepository
import com.example.comusenias.domain.repositories.SubLevels
import kotlinx.coroutines.flow.Flow

class SubLevelRepositoryImpl(private val subLevelDao: SubLevelDao) : SubLevelRepository {

    override fun getAllSubLevel(): Flow<SubLevels> = subLevelDao.getSubLevel()

    override suspend fun getSubLevelById(idSubLevel: String): SubLevelEntity =
        subLevelDao.getSubLevelById(idSubLevel)

    override suspend fun insertSubLevel(subLevelEntity: List<SubLevelEntity>) =
        subLevelDao.insertSubLevel(subLevelEntity)

    override suspend fun updateSubLevel(subLevelEntity: SubLevelEntity) =
        subLevelDao.updateSubLevel(subLevelEntity)

    override suspend fun deleteSubLevel(subLevelEntity: SubLevelEntity) =
        subLevelDao.deleteSubLevel(subLevelEntity)
}