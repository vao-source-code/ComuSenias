package com.ars.comusenias.data.repositories.firebase

import android.net.Uri
import com.ars.comusenias.constants.FirebaseConstants.USERS_COLLECTION
import com.ars.comusenias.domain.library.LibraryPassword
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.repositories.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERS_COLLECTION) private val usersRef: CollectionReference,
    @Named(USERS_COLLECTION) private val storageUsersRef: StorageReference
) : UsersRepository {

    /** Create a new user in the database
     * @param user: UserModel
     * @return Response<Boolean>
     * */
    override suspend fun createUser(user: UserModel): Response<Boolean> {
        return try {
            usersRef.document(user.id).set(user).await()
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
    override fun getUserById(id: String): Flow<UserModel> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(UserModel::class.java) ?: UserModel()
            user.password = LibraryPassword.encodePassword(user.password)
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun updateUser(user: UserModel): Response<Boolean> {
        return try {
            val mapImage: MutableMap<String, Any> = HashMap()
            //aca tiene que ser email y rol
            mapImage["email"] = user.email
            mapImage["rol"] = user.rol
            mapImage["password"] = LibraryPassword.hashPassword(user.password)
            //mapImage["image"] = user.image?.let { it } ?: ""
            usersRef.document(user.id).update(mapImage).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(file.name)
            val url = ref.downloadUrl.await()
            return Response.Success(url.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }
}