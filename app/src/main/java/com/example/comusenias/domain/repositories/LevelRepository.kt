package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.model.game.LevelModel
import com.example.comusenias.domain.models.model.game.SubLevelModel
import kotlinx.coroutines.flow.Flow

interface LevelRepository {
    fun searchLevelById(id: String): Flow<LevelModel>
    fun getLevels(): Flow<Response<List<LevelModel>>>
    fun searchSubLevels(idLevel: String): Flow<List<SubLevelModel>>
}