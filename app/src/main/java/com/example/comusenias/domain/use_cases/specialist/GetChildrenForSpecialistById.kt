package com.example.comusenias.domain.use_cases.specialist

import com.example.comusenias.domain.repositories.SpecialistRepository
import javax.inject.Inject

class GetChildrenForSpecialistById @Inject constructor(private val specialistRepository: SpecialistRepository) {
    suspend operator fun invoke(id: String) = specialistRepository.getChildrenForSpecialistById(id)
}