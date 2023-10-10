package com.example.comusenias.domain.use_cases.game

import com.example.comusenias.domain.repositories.GameRepository
import javax.inject.Inject

class SearchGame @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun  invoke(id : String) = gameRepository.searchGame(id)
}