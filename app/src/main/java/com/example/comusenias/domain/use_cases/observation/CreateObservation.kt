package com.example.comusenias.domain.use_cases.observation

import com.example.comusenias.domain.models.observation.ObservationModel
import com.example.comusenias.domain.repositories.ObservationRepository
import javax.inject.Inject

class CreateObservation @Inject constructor(private val repository: ObservationRepository) {
    suspend operator fun
            invoke(observation: ObservationModel) = repository.createObservation(observation)
}
