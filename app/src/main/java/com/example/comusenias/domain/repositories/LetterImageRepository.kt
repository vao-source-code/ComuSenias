package com.example.comusenias.domain.repositories

import java.io.File


interface LetterImageRepository {

    suspend fun getLetterImage(letter: Char): File
}