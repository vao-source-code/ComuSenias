package com.example.comusenias.data.api

import com.example.comusenias.domain.models.VowelsResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {
    @Multipart
    @POST("processing_vowels")
    suspend fun uploadFile(
        @Part filePart: MultipartBody.Part
    ):VowelsResponse
}