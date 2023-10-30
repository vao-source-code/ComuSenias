package com.example.comusenias.domain.models.response

/**
 * Creao la clase response porque no tengo idea de que trae realmente firebase
 */
sealed class Response<out T>{
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception?) : Response<Nothing>()
    data class CustomError(val exception: String?) : Response<String>()
    object Loading : Response<Nothing>()
}