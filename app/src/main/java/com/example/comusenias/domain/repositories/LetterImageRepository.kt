package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.letter.LetterModel
import kotlinx.coroutines.flow.Flow
import java.io.File


interface LetterImageRepository {
    suspend fun getLetterImage(letter: String): File
    suspend fun searchLetterImage(letter: String): Flow<Response<List<LetterModel>>>
}