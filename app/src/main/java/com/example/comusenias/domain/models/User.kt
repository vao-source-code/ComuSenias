package com.example.comusenias.domain.models

import com.example.comusenias.domain.library.LibraryPassword
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


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
            if(image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else ""
        )
    )

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}