package com.example.comusenias.domain.models.model

import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.google.gson.Gson


data class UserModel(
    var id: String = "",
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var image: String = "",
) {
    fun toJson(): String = Gson().toJson(
        UserModel(
            id,
            userName,
            email,
            LibraryPassword.encodePassword(password),
            if(image != "") LibraryString.encodeURL(image) else ""
        )
    )

    companion object {
        fun fromJson(data: String): UserModel = Gson().fromJson(data, UserModel::class.java)
    }

}