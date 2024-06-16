package com.ars.comusenias.domain.repositories

import com.ars.comusenias.domain.models.vowels.VowelsResponse
import okhttp3.MultipartBody

interface GalleryRepository {
    suspend fun showVowels(file: MultipartBody.Part): VowelsResponse
}