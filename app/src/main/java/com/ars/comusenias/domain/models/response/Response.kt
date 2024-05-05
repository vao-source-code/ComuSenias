package com.ars.comusenias.domain.models.response

/**
 * Creao la clase response porque no tengo idea de que trae realmente firebase
 */
sealed class Response<out T>{
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception?) : Response<Nothing>()
    data class ErrorFirebase(val errorCode: String?) : Response<Nothing>()

    data class CustomError(val exception: String?) : Response<String>()
    object Loading : Response<Nothing>()
}

enum class ErrorCode {
    ERROR_USER_NOT_FOUND,
    ERROR_WRONG_PASSWORD;

    companion object {
        fun getErrorMessage(errorCode: String): String {
            return when (errorCode) {
                ERROR_USER_NOT_FOUND.toString() -> "Usuario inválido"
                ERROR_WRONG_PASSWORD.toString() -> "Contraseña incorrecta"
                else -> {""}
            }
        }
    }
}
