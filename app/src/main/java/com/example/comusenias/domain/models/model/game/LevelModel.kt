package com.example.comusenias.domain.models.model.game

import com.google.gson.Gson

data class LevelModel(
    var id: String = "",
    var name: String = "",
    var subLevel: ArrayList<String> = ArrayList(),
    var subLevelModel: ArrayList<SubLevelModel> = ArrayList(),
) {
    fun toJson(): String = Gson().toJson(LevelModel(id, name, subLevel, subLevelModel))

    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        const val ID = "id"
        const val NAME = "name"
        const val SUB_LEVEL = "subLevel"

        fun fromJson(data: String): LevelModel = Gson().fromJson(data, LevelModel::class.java)

    }
}