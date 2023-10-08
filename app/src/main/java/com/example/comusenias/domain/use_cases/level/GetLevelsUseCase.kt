package com.example.comusenias.domain.use_cases.level

import com.example.comusenias.domain.repositories.LevelRepository
import javax.inject.Inject

class GetLevelsUseCase @Inject constructor(private val repository: LevelRepository) {
    suspend operator fun invoke() = repository.getLevels()
}