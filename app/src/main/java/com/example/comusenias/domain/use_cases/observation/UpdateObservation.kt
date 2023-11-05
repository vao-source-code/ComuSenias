package com.example.comusenias.domain.use_cases.observation

import com.example.comusenias.domain.models.observation.ObservationModel
import com.example.comusenias.domain.repositories.ObservationRepository
import javax.inject.Inject

class UpdateObservation @Inject constructor(private val observationRepository: ObservationRepository) {
    suspend operator fun invoke(observation: ObservationModel) =
        observationRepository.updateObservation(observation)
}