package com.example.comusenias.data.repositories

import com.example.comusenias.constants.FirebaseConstants
import com.example.comusenias.domain.models.Letters
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.repositories.LetterImageRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named


class LetterImageRepositoryImpl @Inject constructor(
    @Named(FirebaseConstants.LETTERS_COLLECTION) private val storageLettersRef: StorageReference,
    @Named(FirebaseConstants.LETTERS_COLLECTION) private val lettersRef: CollectionReference,
    ) : LetterImageRepository {

    override suspend fun getLetterImage(letter: String): File {
        return try {
            val image = storageLettersRef.child(letter)
            val bytes = image.getBytes(640000).await()
            val file = File(image.name)
            file
        } catch (e: Exception) {
            e.printStackTrace()
            File("")
        }
    }

    /**
     * Busca imágenes de letras según la letra proporcionada.
     *
     * @param letter La letra a buscar. Se debe usar una letra de AphabetConstants.
     * @return Flow emitting emitiendo una respuesta con una lista de letras y sus imágenes.
     */
    override suspend fun searchLetterImage(letter: String): Flow<Response<List<Letters>>> =
        callbackFlow {
            val snapshotListener = lettersRef.whereEqualTo(Letters.FIELD_LETTER, letter)
                .addSnapshotListener { snapshot, e ->
                    val postsResponse = if (snapshot != null) {
                        val posts = snapshot.documents.map { document ->
                            Letters(
                                id = document.id,
                                letter = document.data?.get(Letters.FIELD_LETTER) as String,
                                image = document.data?.get(Letters.FIELD_IMAGE) as String
                            )
                        }.toMutableList()

                        Response.Success(posts)
                    } else {
                        Response.Error(e)
                    }
                    trySend(postsResponse)
                }
            awaitClose {
                snapshotListener.remove()
            }
        }

}