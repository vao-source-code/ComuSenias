package com.example.comusenias.data.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.comusenias.data.api.ApiService
import com.example.comusenias.domain.models.VowelsResponse
import com.example.comusenias.domain.repositories.GalleryRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val apiService: ApiService):GalleryRepository {
    override suspend fun showVowels(filePart: MultipartBody.Part): VowelsResponse {
        val response = apiService.uploadFile(filePart)

        if (response.isSuccessful) {
            val responseBody = response.body()

            Log.d("Retrofit", "Response Body: ${response.body()!!.letra}")

            return VowelsResponse(responseBody!!.letra,responseBody.image_base64)

        } else {
            Log.d("Retrofit", "Unsuccessful response: ${response.code()}")
        }
        return VowelsResponse("","")
    }
}