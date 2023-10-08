package com.example.comusenias.domain.models.model

import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class UserModel(
    var id: String = EMPTY_STRING,
    var userName: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var password: String = EMPTY_STRING,
    var image: String = EMPTY_STRING,
) {
    fun toJson(): String = Gson().toJson(
        UserModel(
            id,
            userName,
            email,
            LibraryPassword.encodePassword(password),
            if (image != EMPTY_STRING) LibraryString.encodeURL(image) else EMPTY_STRING
        )
    )

    companion object {
        fun fromJson(data: String): UserModel = Gson().fromJson(data, UserModel::class.java)
    }
}