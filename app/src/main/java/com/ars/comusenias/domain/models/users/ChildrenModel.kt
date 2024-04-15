package com.ars.comusenias.domain.models.users

import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.models.observation.ObservationModel
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class ChildrenModel(
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var phone: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var image: String? = EMPTY_STRING,
    var date: String = EMPTY_STRING,
    var idSpecialist: String = EMPTY_STRING,
    var isPremium: Boolean = false,
    var levels: List<LevelModel> = listOf(),
    var observation :List<ObservationModel> = emptyList(),
) {
    fun toJson(): String = Gson().toJson(
        ChildrenModel(
            id = id,
            name = name,
            phone = phone,
            email = email,
            image = image,
            date = date,
            idSpecialist = idSpecialist,
            isPremium = isPremium,
        )
    )

    companion object {
        fun fromJson(data: String): ChildrenModel = Gson().fromJson(data, ChildrenModel::class.java)
    }
}
