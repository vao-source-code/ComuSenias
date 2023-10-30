package com.example.comusenias.domain.use_cases.children

import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.repositories.ChildrenRepository
import javax.inject.Inject

class UpdateChildren @Inject constructor(private val repository: ChildrenRepository) {
    suspend operator fun invoke(user: ChildrenModel) = repository.updateUserChildren(user)
}