package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.letter.LetterModel
import kotlinx.coroutines.flow.Flow
import java.io.File


interface LetterImageRepository {
    suspend fun getLetterImage(letter: String): File
    suspend fun searchLetterImage(letter: String): Flow<Response<List<LetterModel>>>
}