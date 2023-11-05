package com.example.comusenias.domain.use_cases.specialist

import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.repositories.SpecialistRepository
import javax.inject.Inject

class UpdateSpecialist @Inject constructor(private val specialistRepository: SpecialistRepository) {
    suspend operator fun invoke(user: SpecialistModel) =
        specialistRepository.updateUserSpecialist(user)
}