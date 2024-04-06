package com.ars.comusenias.domain.use_cases.observation

import com.ars.comusenias.domain.models.observation.ObservationModel
import com.ars.comusenias.domain.repositories.ObservationRepository
import javax.inject.Inject

class UpdateObservation @Inject constructor(private val observationRepository: ObservationRepository) {
    suspend operator fun invoke(observation: ObservationModel) =
        observationRepository.updateObservation(observation)
}