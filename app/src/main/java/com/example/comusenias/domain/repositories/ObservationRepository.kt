package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.observation.Observation
import com.example.comusenias.domain.models.response.Response
import kotlinx.coroutines.flow.Flow

interface ObservationRepository {
    suspend fun createObservation(observation: Observation): Response<Boolean>
    fun getObservationById(id: String): Flow<Observation>
    suspend fun updateObservation(observation: Observation): Response<Boolean>
    fun getObservation(idChildren: String): Flow<List<Observation>>

}