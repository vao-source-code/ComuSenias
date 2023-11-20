package com.example.comusenias.data.repositories

import android.net.Uri
import com.example.comusenias.constants.FirebaseConstants.CHILDREN_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.SPECIALIST_COLLECTION
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.repositories.SpecialistRepository
import com.example.comusenias.presentation.ui.theme.DATE
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.IMAGE
import com.example.comusenias.presentation.ui.theme.MEDICAL_LICENSE
import com.example.comusenias.presentation.ui.theme.MEDICAL_LICENSE_EXPIRATION
import com.example.comusenias.presentation.ui.theme.NAME
import com.example.comusenias.presentation.ui.theme.SPECIALITY
import com.example.comusenias.presentation.ui.theme.TEL
import com.example.comusenias.presentation.ui.theme.TITLE_MEDICAL
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class SpecialistRepositoryImpl @Inject constructor(
    @Named(SPECIALIST_COLLECTION) private val specialistRef: CollectionReference,
    @Named(SPECIALIST_COLLECTION) private val storageSpecialistRef: StorageReference,
    @Named(CHILDREN_COLLECTION) private val childrenRef: CollectionReference
) : SpecialistRepository {

    override suspend fun saveImageUserSpecialist(file: File): Response<String> {
        return try {
            val url = uploadFileToStorage(file)
            Response.Success(url)
        } catch (e: Exception) {
            handleUploadError(e)
        }
    }

    override suspend fun createUserSpecialist(user: SpecialistModel): Response<Boolean> {
        return try {
            specialistRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            handleUpdateUserError(e)
        }
    }

    override fun getUserSpecialistById(id: String): Flow<SpecialistModel> = callbackFlow {
        val snapshotListener = specialistRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(SpecialistModel::class.java) ?: SpecialistModel()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun updateUserSpecialist(user: SpecialistModel): Response<Boolean> {
        return try {
            val userMap = createUserMap(user)
            updateUserInDatabase(user.id, userMap)
            Response.Success(true)
        } catch (e: Exception) {
            handleUpdateUserError(e)
        }
    }

    private fun createUserMap(user: SpecialistModel): MutableMap<String, Any> {
        val mapImage: MutableMap<String, Any> = HashMap()
        mapImage[NAME] = user.name
        mapImage[IMAGE] = user.image ?: EMPTY_STRING
        mapImage[DATE] = user.date
        mapImage[TEL] = user.tel
        mapImage[MEDICAL_LICENSE] = user.medicalLicense
        mapImage[SPECIALITY] = user.speciality
        mapImage[TITLE_MEDICAL] = user.titleMedical
        mapImage[MEDICAL_LICENSE_EXPIRATION] = user.medicalLicenseExpiration
        return mapImage
    }

    private suspend fun updateUserInDatabase(userId: String, userMap: Map<String, Any>) {
        specialistRef.document(userId).update(userMap).await()
    }

    private fun handleUpdateUserError(e: Exception): Response<Boolean> {
        e.printStackTrace()
        return Response.Error(e)
    }

    override suspend fun getChildrenForSpecialistById(id: String): Flow<Response<List<ChildrenModel>>> =
        callbackFlow {
            val snapshotListener = childrenRef.whereEqualTo("idSpecialist", id)
                .addSnapshotListener { snapshot, exception ->
                    sendException(exception)
                    sendSuccess(snapshot)
                }
            awaitClose {
                snapshotListener.remove()
            }
        }

    private fun ProducerScope<Response<List<ChildrenModel>>>.sendSuccess(
        snapshot: QuerySnapshot?
    ) {
        snapshot.let {
            val childrenModelList = snapshot?.toObjects(ChildrenModel::class.java)
                ?: ArrayList<ChildrenModel>()
            trySend(Response.Success(childrenModelList))
        }
    }

    private fun ProducerScope<Response<List<ChildrenModel>>>.sendException(
        exception: FirebaseFirestoreException?
    ) {
        exception?.let { e ->
            trySend(Response.Error(e))
        }
    }

    private suspend fun uploadFileToStorage(file: File): String {
        val fromFile = Uri.fromFile(file)
        val ref = storageSpecialistRef.child(file.name)
        uploadFileToStorageRef(fromFile, ref)
        return getDownloadUrlFromStorageRef(ref)
    }

    private suspend fun uploadFileToStorageRef(fromFile: Uri, ref: StorageReference) {
        ref.putFile(fromFile).await()
    }

    private suspend fun getDownloadUrlFromStorageRef(ref: StorageReference): String {
        val url = ref.downloadUrl.await()
        return url.toString()
    }

    private fun handleUploadError(e: Exception): Response<String> {
        e.printStackTrace()
        return Response.Error(e)
    }
}