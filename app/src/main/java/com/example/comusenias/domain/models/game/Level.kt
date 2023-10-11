package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame
import com.google.gson.Gson

data class Level(
    var id: String = "",
    var name: String = "",
    var subLevel: ArrayList<String> = ArrayList(),
    var subLevelModel: ArrayList<SubLevel> = ArrayList(),
    var isCompleted: StatusGame = StatusGame.IN_PROGRESS,
    ){
    fun toJson(): String = Gson().toJson(Level(id, name, subLevel, subLevelModel))

    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        const val ID = "id"
        const val NAME = "name"
        const val SUB_LEVEL = "subLevel"
        const val SUB_LEVEL_MODEL = "subLevelModel"
        fun fromJson(data: String): Level = Gson().fromJson(data, Level::class.java)

    }
}

