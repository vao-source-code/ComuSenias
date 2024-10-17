package com.ars.comusenias.domain.models.users

import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class UserModel(
    var id: String = EMPTY_STRING,
    var name : String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var rol: String = Rol.CHILDREN.toString(),
    var password: String = EMPTY_STRING,

    ) {
    fun toJson(): String = Gson().toJson(
        UserModel(
            id,
            name,
            email,
            rol,
            password,
        )
    )

    companion object {
        fun fromJson(data: String): UserModel = Gson().fromJson(data, UserModel::class.java)
    }
}

enum class Rol {
    SPECIALIST, CHILDREN
}