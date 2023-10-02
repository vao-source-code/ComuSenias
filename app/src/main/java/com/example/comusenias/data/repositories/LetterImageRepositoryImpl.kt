package com.example.comusenias.data.repositories

import com.example.comusenias.constants.FirebaseConstants
import com.example.comusenias.domain.repositories.LetterImageRepository
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class LetterImageRepositoryImpl @Inject constructor(
    @Named(FirebaseConstants.LETTERS_COLLECTION) private val storageLettersRef: StorageReference) : LetterImageRepository {

    override suspend fun getLetterImage(letter: String): File {
        return try {
            //CREO QE NO ES NECESARIO REMARCAR EL DISPATCHER
            val image = storageLettersRef.child(letter)
            val bytes = image.getBytes(640000).await()

            val file = File(image.name)
            /*withContext(Dispatchers.IO) {
                FileOutputStream(file).use { fos ->
                    fos.write(bytes)
                }
            }*/

            file
        } catch (e: Exception) {
            e.printStackTrace()
            File("")
        }
    }
}