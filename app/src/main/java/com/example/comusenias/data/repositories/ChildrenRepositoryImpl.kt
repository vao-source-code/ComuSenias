package com.example.comusenias.data.repositories

import android.net.Uri
import com.example.comusenias.constants.FirebaseConstants
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.repositories.ChildrenRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class ChildrenRepositoryImpl @Inject constructor(
    @Named(FirebaseConstants.CHILDREN_COLLECTION) private val childrenRef: CollectionReference,
    @Named(FirebaseConstants.CHILDREN_COLLECTION) private val storageChildrenRef: StorageReference
) : ChildrenRepository {

    override suspend fun createUserChildren(user: ChildrenModel): Response<Boolean> {
        return try {
            childrenRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }

    /** Get a user by id
     * @param id: String
     * @return Flow<UserModel>
     * */
    override fun getUserChildrenById(id: String): Flow<ChildrenModel> = callbackFlow {
        val snapshotListener = childrenRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(ChildrenModel::class.java) ?: ChildrenModel()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun updateUserChildren(user: ChildrenModel): Response<Boolean> {
        return try {
            val mapImage: MutableMap<String, Any> = HashMap()
            mapImage["name"] = user.name
            mapImage["date"] = user.date
            mapImage["idSpecialist"] = user.idSpecialist
            mapImage["tel"] = user.tel
            mapImage["image"] = user.image?.let { it } ?: ""
            childrenRef.document(user.id).update(mapImage).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }

    override suspend fun saveImageUserChildren(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageChildrenRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            return Response.Success(url.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }

}