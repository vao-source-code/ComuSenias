package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.model.game.GameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun searchGame(idGame: String): Flow<GameModel>
    suspend fun searchBySublevelId(idSubLevel: String) : Flow<GameModel>
}