package com.example.comusenias.domain.use_cases.level

import com.example.comusenias.domain.repositories.LevelRepository
import javax.inject.Inject

class SearchLevelName @Inject constructor(private val repository: LevelRepository) {
    operator fun invoke(name: String) = repository.searchLevelName(name)
}