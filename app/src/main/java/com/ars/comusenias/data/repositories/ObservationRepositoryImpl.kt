package com.ars.comusenias.data.repositories

import com.ars.comusenias.constants.FirebaseConstants.OBSERVATION_COLLECTION
import com.ars.comusenias.domain.models.observation.ObservationModel
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.repositories.ObservationRepository
import com.ars.comusenias.presentation.ui.theme.DATE_OBSERVATION
import com.ars.comusenias.presentation.ui.theme.ID
import com.ars.comusenias.presentation.ui.theme.ID_CHILDREN
import com.ars.comusenias.presentation.ui.theme.ID_SPECIALIST
import com.ars.comusenias.presentation.ui.theme.OBSERVATION_CHILDREN
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.ProducerScope
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
            updateObservationInDatabase(observation)
            Response.Success(true)
        } catch (e: Exception) {
            handleUpdateObservationError(e)
        }
    }

    private suspend fun updateObservationInDatabase(observation: ObservationModel) {
        val mapImage = createMapFromObservation(observation)
        observationRef.document(observation.id).update(mapImage).await()
    }

    private fun createMapFromObservation(observation: ObservationModel): MutableMap<String, Any> {
        val mapImage: MutableMap<String, Any> = HashMap()
        mapImage[ID] = observation.id
        mapImage[DATE_OBSERVATION] = observation.dateObservation
        mapImage[ID_SPECIALIST] = observation.idSpecialist
        mapImage[ID_CHILDREN] = observation.idChildren
        mapImage[OBSERVATION_CHILDREN] = observation.observation
        return mapImage
    }

    private fun handleUpdateObservationError(e: Exception): Response<Boolean> {
        e.printStackTrace()
        return Response.Error(e)
    }

    override fun getObservation(idChildren: String): Flow<Response<List<ObservationModel>>> =
        callbackFlow {
            val snapshotListener = observationRef.whereEqualTo("idChildren", idChildren)
                .addSnapshotListener { snapshot, firebaseException ->
                    snapShotListener(firebaseException, snapshot)
                }
            awaitClose {
                snapshotListener.remove()
            }
        }

    private fun ProducerScope<Response<List<ObservationModel>>>.snapShotListener(
        firebaseException: FirebaseFirestoreException?,
        snapshot: QuerySnapshot?
    ) {
        tryResponseError(firebaseException)
        sendResponseSuccess(snapshot)
    }

    private fun ProducerScope<Response<List<ObservationModel>>>.tryResponseError(
        firebaseException: FirebaseFirestoreException?
    ) {
        firebaseException?.let {
            trySend(Response.Error(it))
        }
    }

    private fun ProducerScope<Response<List<ObservationModel>>>.sendResponseSuccess(
        snapshot: QuerySnapshot?
    ) {
        snapshot.let {
            val sortedModelList = snapshot?.toObjects(ObservationModel::class.java)
                ?.mapNotNull { it }
                ?.sortedByDescending { it.timeDate }
                ?: emptyList()
            trySend(Response.Success(sortedModelList))
        }
    }
}