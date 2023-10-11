package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.Level
import com.example.comusenias.domain.models.game.SubLevel

import kotlinx.coroutines.flow.Flow

interface LevelRepository {
    fun searchLevelById(id: String): Flow<Level>
    fun getLevels(): Flow<Response<List<Level>>>
    fun searchSubLevels(idLevel: String): Flow<List<SubLevel>>
}