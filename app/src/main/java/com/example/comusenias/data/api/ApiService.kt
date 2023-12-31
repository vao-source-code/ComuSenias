package com.example.comusenias.data.api

import com.example.comusenias.domain.models.vowels.VowelsResponse
import com.example.comusenias.presentation.ui.theme.PROCESSING_VOWELS
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST(PROCESSING_VOWELS)
    suspend fun uploadFile(
        @Part filePart: MultipartBody.Part
    ): Response<VowelsResponse>
}