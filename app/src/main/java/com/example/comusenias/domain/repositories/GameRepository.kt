package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.game.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun searchGame(idGame: String): Flow<Game>
    suspend fun searchBySublevelId(idSubLevel: String) : Flow<Game>
}