package com.example.comusenias.domain.models.state

import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class PacientState(
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var phone: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var date: String = EMPTY_STRING,
    var idSpecialist: String = EMPTY_STRING,
){
    fun toJson(): String = Gson().toJson(
        PacientState(
            id = id,
            name = name,
            phone = phone,
            email = email,
            date = date,
            idSpecialist = idSpecialist,
        )
    )

    companion object {
        fun fromJson(data: String): PacientState = Gson().fromJson(data, PacientState::class.java)
    }
}

