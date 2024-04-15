package com.ars.comusenias.domain.use_cases.shared_preferences

import com.ars.comusenias.domain.repositories.RolDataStorageRepository
import javax.inject.Inject

class GetRolValue @Inject constructor(private val repository: RolDataStorageRepository) {
    suspend operator fun
            invoke(key: String) = repository.getRolValue(key)
}