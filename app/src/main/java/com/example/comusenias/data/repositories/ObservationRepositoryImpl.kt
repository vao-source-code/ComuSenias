package com.example.comusenias.data.repositories

import com.example.comusenias.constants.FirebaseConstants.OBSERVATION_COLLECTION
import com.example.comusenias.domain.models.observation.ObservationModel
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.repositories.ObservationRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ObservationRepositoryImpl @Inject constructor(
    @Named(OBSERVATION_COLLECTION) private val observationRef: CollectionReference,
) : ObservationRepository {

    override suspend fun createObservation(observation: ObservationModel): Response<Boolean> {
        return try {
            observationRef.document().set(observation).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }

    override fun getObservationById(id: String): Flow<ObservationModel> = callbackFlow {
        val snapshotListener = observationRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(ObservationModel::class.java) ?: ObservationModel()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun updateObservation(observation: ObservationModel): Response<Boolean> {
        return try {
            val mapImage: MutableMap<String, Any> = HashMap()
            mapImage["id"] = observation.id
            mapImage["dateObservation"] = observation.dateObservation
            mapImage["idSpecialist"] = observation.idSpecialist
            mapImage["idChildren"] = observation.idChildren
            mapImage["observation"] = observation.observation
            observationRef.document(observation.id).update(mapImage).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }

    override fun getObservation(idChildren: String): Flow<Response<List<ObservationModel>>> =
        callbackFlow {
            val snapshotListener = observationRef.whereEqualTo("idChildren", idChildren)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        trySend(Response.Error(e))
                    }
                    snapshot.let {
                        val modelList = snapshot?.toObjects(ObservationModel::class.java)
                            ?: ArrayList<ObservationModel>()
                        trySend(Response.Success(modelList))
                    }
                }

            awaitClose {
                snapshotListener.remove()
            }
        }


}