package com.ars.comusenias.domain.use_cases.specialist

import com.ars.comusenias.domain.models.users.SpecialistModel
import com.ars.comusenias.domain.repositories.SpecialistRepository
import javax.inject.Inject

class UpdateSpecialist @Inject constructor(private val specialistRepository: SpecialistRepository) {
    suspend operator fun invoke(user: SpecialistModel) =
        specialistRepository.updateUserSpecialist(user)
}