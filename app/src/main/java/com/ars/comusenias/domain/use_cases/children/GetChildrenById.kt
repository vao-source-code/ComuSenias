package com.ars.comusenias.domain.use_cases.children

import com.ars.comusenias.domain.repositories.ChildrenRepository
import javax.inject.Inject

class GetChildrenById @Inject constructor(private val childrenRepository: ChildrenRepository) {
    operator fun invoke(id: String) = childrenRepository.getUserChildrenById(id)
}