package com.ars.comusenias.domain.use_cases.children

import com.ars.comusenias.domain.repositories.ChildrenRepository
import java.io.File
import javax.inject.Inject

class SaveImageChildren @Inject constructor(private val repository: ChildrenRepository) {
    suspend operator fun invoke(file: File) = repository.saveImageUserChildren(file)
}