package com.example.comusenias.domain.models.observation

import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class Observation(
    var id: String = EMPTY_STRING,
    var dateObservation: String = EMPTY_STRING,
    var observation: String = EMPTY_STRING,
    var idChildren: String = EMPTY_STRING,
    var idSpecialist: String = EMPTY_STRING,
    var nameSpecialist: String = EMPTY_STRING,
) {

    fun toJson(): String = Gson().toJson(
        Observation(
            id = id,
            dateObservation = dateObservation,
            observation = observation,
            idChildren = idChildren,
            idSpecialist = idSpecialist,
            nameSpecialist = nameSpecialist
        )
    )

    companion object {
        fun fromJson(data: String): Observation = Gson().fromJson(data, Observation::class.java)
    }
}
