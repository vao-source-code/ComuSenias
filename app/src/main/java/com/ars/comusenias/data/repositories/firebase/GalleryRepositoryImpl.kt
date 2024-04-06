package com.ars.comusenias.data.repositories.firebase

import com.ars.comusenias.data.api.ApiService
import com.ars.comusenias.domain.models.vowels.VowelsResponse
import com.ars.comusenias.domain.repositories.GalleryRepository
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import okhttp3.MultipartBody
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GalleryRepository {
    /**
     * Carga un archivo al servidor y muestra las vocales obtenidas en respuesta.
     *
     * @param file Parte del cuerpo de la petición que contiene el archivo a cargar.
     * @return VowelsResponse que contiene las vocales obtenidas y la imagen en base64.
     * En caso de una respuesta no exitosa o cuerpo de respuesta nulo, devuelve VowelsResponse vacío.
     */
    override suspend fun showVowels(file: MultipartBody.Part): VowelsResponse {
        val response = apiService.uploadFile(file)
        return if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                VowelsResponse(responseBody.letra, responseBody.image_base64)
            } ?: emptyVowelsResponse()
        } else {
            emptyVowelsResponse()
        }
    }

    /**
     * Crea y devuelve un VowelsResponse vacío.
     *
     * @return VowelsResponse vacío.
     */
    private fun emptyVowelsResponse() = VowelsResponse(EMPTY_STRING, EMPTY_STRING)
}