package com.ars.comusenias.data.repositories.firebase

import com.ars.comusenias.data.room.dao.SubLevelDao
import com.ars.comusenias.domain.models.room.SubLevelEntity
import com.ars.comusenias.domain.repositories.SubLevelRepository
import com.ars.comusenias.domain.repositories.SubLevels
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