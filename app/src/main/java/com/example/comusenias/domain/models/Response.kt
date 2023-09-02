package com.example.comusenias.domain.models

/**
 * Creao la clase response porque no tengo idea de que trae realmente firebase
 */
sealed class Response<out T>{
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception?) : Response<Nothing>()
    object Loading : Response<Nothing>()
}