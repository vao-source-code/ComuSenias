package com.example.comusenias.domain.use_cases.observation

data class ObservationFactory(
    val createObservation: CreateObservation,
    val getObservationById: GetObservationById,
    val updateObservation: UpdateObservation,
    val getObservation: GetObservation
)
