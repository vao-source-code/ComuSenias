package com.example.comusenias.domain.use_cases.specialist

import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.repositories.SpecialistRepository
import javax.inject.Inject


class CreateSpecialist @Inject constructor(private val repository: SpecialistRepository) {
    suspend operator fun
            invoke(user: SpecialistModel) = repository.createUserSpecialist(user)
}
