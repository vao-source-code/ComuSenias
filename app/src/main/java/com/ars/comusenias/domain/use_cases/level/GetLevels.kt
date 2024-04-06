package com.ars.comusenias.domain.use_cases.level

import com.ars.comusenias.domain.repositories.LevelRepository
import javax.inject.Inject

class GetLevels @Inject constructor(private val repository: LevelRepository) {
    suspend operator fun invoke() = repository.getLevels()
}