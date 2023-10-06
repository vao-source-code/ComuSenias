package com.example.comusenias.domain.models.user

import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.google.gson.Gson

open class User(
    var id : String = "",
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var image: String = "",
) {

    fun toJson(): String = Gson().toJson(
        User(
            name,
            lastName,
            email,
            LibraryPassword.encodePassword(password),
            if(image != "") LibraryString.encodeURL(image) else ""
        )
    )

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}