package com.ars.comusenias.domain.models.observation

import org.junit.Assert.assertEquals
import org.junit.Test

class ObservationTest {
    @Test
    fun shouldCreateAObservationObjectWithAllProperties() {
        val id = "1234567890"
        val dateObservation = "2023-10-08"
        val observation = "This is an observation."

        val observationModel = ObservationModel(
            id = id,
            dateObservation = dateObservation,
            observation = observation
        )

        assertEquals(id, observationModel.id)
        assertEquals(dateObservation, observationModel.dateObservation)
        assertEquals(observation, observationModel.observation)
    }
}