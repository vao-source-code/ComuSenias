package com.example.comusenias.domain.repositories

import com.example.comusenias.domain.models.VowelsResponse
import okhttp3.MultipartBody

interface GalleryRepository {
    suspend fun showVowels(file:MultipartBody.Part):VowelsResponse
}