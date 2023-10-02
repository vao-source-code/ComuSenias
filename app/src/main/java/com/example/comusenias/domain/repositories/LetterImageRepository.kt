package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.Letters
import com.example.comusenias.domain.models.Response
import kotlinx.coroutines.flow.Flow
import java.io.File


interface LetterImageRepository {
    suspend fun getLetterImage(letter: String): File
    suspend fun searchLetterImage(letter: String): Flow<Response<List<Letters>>>
}