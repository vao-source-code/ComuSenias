package com.ars.comusenias.domain.use_cases.gallery

import com.ars.comusenias.domain.repositories.GalleryRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class GalleryUseCases @Inject constructor(private val repository: GalleryRepository) {
    suspend fun showVowels(fileMultipartBody: MultipartBody.Part) =
        repository.showVowels(file = fileMultipartBody)
}