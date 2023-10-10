package com.example.comusenias.domain.use_cases.game

import com.example.comusenias.domain.repositories.GameRepository
import javax.inject.Inject

class SearchBySublevelId @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun  invoke(idSublevelId : String) = gameRepository.searchBySublevelId(idSublevelId)
}