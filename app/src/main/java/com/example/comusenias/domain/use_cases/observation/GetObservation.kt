package com.example.comusenias.domain.use_cases.observation

import com.example.comusenias.domain.repositories.ObservationRepository
import javax.inject.Inject

class GetObservation @Inject constructor(private val observationRepository: ObservationRepository) {
    suspend operator fun invoke(idChildren: String) =
        observationRepository.getObservation(idChildren)
}