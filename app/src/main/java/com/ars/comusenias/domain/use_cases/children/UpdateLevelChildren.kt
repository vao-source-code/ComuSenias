package com.ars.comusenias.domain.use_cases.children

import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.repositories.ChildrenRepository
import javax.inject.Inject

class UpdateLevelChildren @Inject constructor(private val repository: ChildrenRepository) {
    suspend operator fun invoke(user: ChildrenModel) = repository.updateLevelChildren(user)
}