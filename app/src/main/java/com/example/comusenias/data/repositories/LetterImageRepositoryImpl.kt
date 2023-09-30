package com.example.comusenias.data.repositories

import com.example.comusenias.domain.repositories.LetterImageRepository
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class LetterImageRepositoryImpl @Inject constructor(
    private val storage: StorageReference
) : LetterImageRepository {

    override suspend fun getLetterImage(letter: Char): File {
        return try {
            //CREO QE NO ES NECESARIO REMARCAR EL DISPATCHER
            val image = storage.child(letter.toString())
            val bytes = image.getBytes(640000).await()

            val file = File(image.name)
            withContext(Dispatchers.IO) {
                FileOutputStream(file).use { fos ->
                    fos.write(bytes)
                }
            }

            file
        } catch (e: Exception) {
            e.printStackTrace()
            File("")
        }
    }
}