package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.models.game.SubLevelModel

import kotlinx.coroutines.flow.Flow

interface LevelRepository {
    fun searchLevelName(name: String): Flow<LevelModel>
    fun searchLevelById(id: String): Flow<LevelModel>
    fun getLevels(): Flow<Response<List<LevelModel>>>
    fun searchSubLevels(idLevel: String): Flow<List<SubLevelModel>>
}