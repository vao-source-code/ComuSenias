package com.example.comusenias.domain.models.users

import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class UserModel(
    var id: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var rol: String = EMPTY_STRING,
    var password: String = EMPTY_STRING,

    ) {
    fun toJson(): String = Gson().toJson(
        UserModel(
            id,
            email,
            LibraryPassword.encodePassword(password),

            )
    )

    companion object {
        fun fromJson(data: String): UserModel = Gson().fromJson(data, UserModel::class.java)
    }
}

enum class Rol {
    SPECIALIST, CHILDREN
}