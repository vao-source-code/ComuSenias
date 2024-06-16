package com.ars.comusenias.domain.use_cases.observation

import com.ars.comusenias.domain.repositories.ObservationRepository
import javax.inject.Inject

class GetObservationById @Inject constructor(private val observationRepository: ObservationRepository) {
    operator fun invoke(id: String) = observationRepository.getObservationById(id)
}