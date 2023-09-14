package com.example.comusenias.domain.models

import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.google.gson.Gson


data class User(
    var id: String = "",
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var image: String = "",
) {
    fun toJson(): String = Gson().toJson(
        User(
            id,
            userName,
            email,
            LibraryPassword.encodePassword(password),
            if(image != "") LibraryString.encodeURL(image) else ""
        )
    )

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}