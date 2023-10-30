package com.example.comusenias.domain.models.observation

import com.example.comusenias.domain.models.users.UserModel
import com.google.gson.Gson

data class Observation(
    var id: String,
    var dateObservation: String,
    var observation: String
) {

    fun toJson(): String = Gson().toJson(
        UserModel(
            id,
            dateObservation,
            observation
        )
    )

    companion object {
        fun fromJson(data: String): UserModel = Gson().fromJson(data, UserModel::class.java)
    }
}
