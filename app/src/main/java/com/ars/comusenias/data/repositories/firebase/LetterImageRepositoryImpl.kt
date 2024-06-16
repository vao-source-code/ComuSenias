package com.ars.comusenias.data.repositories.firebase

import com.ars.comusenias.constants.FirebaseConstants
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.letter.LetterModel
import com.ars.comusenias.domain.repositories.LetterImageRepository
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
            val file = File(image.name)
            file
        } catch (e: Exception) {
            e.printStackTrace()
            File(EMPTY_STRING)
        }
    }

    /**
     * Busca imágenes de letras según la letra proporcionada.
     *
     * @param letter La letra a buscar. Se debe usar una letra de AphabetConstants.
     * @return Flow emitting emitiendo una respuesta con una lista de letras y sus imágenes.
     */
    override suspend fun searchLetterImage(letter: String): Flow<Response<List<LetterModel>>> =
        callbackFlow {
            val snapshotListener = lettersRef.whereEqualTo(LetterModel.FIELD_LETTER, letter)
                .addSnapshotListener { snapshot, e ->
                    val postsResponse = snapshot?.let { querySnapshot ->
                        val putLetter = querySnapshot.documents.map { document ->
                            LetterModel(
                                id = document.id,
                                letter = document.data?.get(LetterModel.FIELD_LETTER) as String,
                                image = document.data?.get(LetterModel.FIELD_IMAGE) as String
                            )
                        }.toMutableList()
                        Response.Success(putLetter)
                    } ?: Response.Error(e)
                    trySend(postsResponse)
                }
            awaitClose {
                snapshotListener.remove()
            }
        }
}