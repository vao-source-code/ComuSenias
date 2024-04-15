package com.ars.comusenias.domain.models.observation

import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class ObservationModel(
    var id: String = EMPTY_STRING,
    var dateObservation: String = EMPTY_STRING,
    var observation: String = EMPTY_STRING,
    var idChildren: String = EMPTY_STRING,
    var idSpecialist: String = EMPTY_STRING,
    var nameSpecialist: String = EMPTY_STRING,
    var timeDate: Long = 0
) {

    fun toJson(): String = Gson().toJson(
        ObservationModel(
            id = id,
            dateObservation = dateObservation,
            observation = observation,
            idChildren = idChildren,
            idSpecialist = idSpecialist,
            nameSpecialist = nameSpecialist,
            timeDate = timeDate
        )
    )

    companion object {
        fun fromJson(data: String): ObservationModel =
            Gson().fromJson(data, ObservationModel::class.java)
    }
}
