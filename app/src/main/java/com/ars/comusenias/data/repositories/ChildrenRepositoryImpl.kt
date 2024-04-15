package com.ars.comusenias.data.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.ars.comusenias.constants.FirebaseConstants
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.repositories.ChildrenRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
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
            mapImage["phone"] = user.phone
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
            // Comprimir el archivo de imagen antes de cargarlo
            val compressedFile = compressImage(file)

            // Obtener URI del archivo comprimido
            val fromFile = Uri.fromFile(compressedFile)

            // Subir el archivo comprimido
            val ref = storageChildrenRef.child(compressedFile.name)
            val uploadTask = ref.putFile(fromFile).await()

            // Obtener URL de descarga
            val url = ref.downloadUrl.await()
            Response.Success(url.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }


    suspend fun compressImage(inputFile: File): File {
        val outputFile = File(inputFile.parent, "${inputFile.nameWithoutExtension}_compressed.jpg")
        try {
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = false
            }
            val bitmap = BitmapFactory.decodeFile(inputFile.path, options)

            val outputStream = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 15, outputStream) // Comprimimos al 15% de calidad
            outputStream.close()

            bitmap.recycle()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return outputFile
    }

    override suspend fun integrateChildrenWithSpecialist(user: ChildrenModel): Response<Boolean> {
        return try {
            val mapImage: MutableMap<String, Any> = HashMap()
            mapImage["idSpecialist"] = user.idSpecialist
            childrenRef.document(user.id).update(mapImage).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }

    override suspend fun updateLevelChildren(user: ChildrenModel): Response<Boolean> {
        return try {
            val mapImage: MutableMap<String, Any> = HashMap()
            mapImage["levels"] = user.levels
            childrenRef.document(user.id).update(mapImage).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }
}
