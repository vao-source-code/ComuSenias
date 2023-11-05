package com.example.comusenias.domain.use_cases.specialist

import com.example.comusenias.domain.repositories.SpecialistRepository
import javax.inject.Inject

class GetSpecialistById @Inject constructor(private val specialistRepository: SpecialistRepository) {
    operator fun invoke(id: String) = specialistRepository.getUserSpecialistById(id)
}