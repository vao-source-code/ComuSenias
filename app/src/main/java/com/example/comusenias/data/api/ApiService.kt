package com.example.comusenias.data.api

import com.example.comusenias.domain.models.VowelsResponse
import okhttp3.MultipartBody
import okhttp3.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {
    @Multipart
    @POST("endpoint")
    suspend fun uploadFile(
        @Part filePart: MultipartBody.Part
    ):VowelsResponse
}