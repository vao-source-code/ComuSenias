package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.observation.ObservationModel
import com.ars.comusenias.domain.models.response.Response
import kotlinx.coroutines.flow.Flow

interface ObservationRepository {
    suspend fun createObservation(observation: ObservationModel): Response<Boolean>
    fun getObservationById(id: String): Flow<ObservationModel>
    suspend fun updateObservation(observation: ObservationModel): Response<Boolean>
    fun getObservation(idChildren: String): Flow<Response<List<ObservationModel>>>
}