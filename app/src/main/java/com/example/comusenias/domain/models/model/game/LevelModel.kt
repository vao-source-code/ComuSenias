package com.example.comusenias.domain.models.model.game

import com.google.gson.Gson

data class LevelModel(
        var id: String = "",
        var name: String = "",
        var subNivel: ArrayList<String> = ArrayList(),
        var subLevelModel: ArrayList<SubLevelModel> = ArrayList(),
) {

    fun toJson(): String = Gson().toJson(LevelModel(id, name, subNivel, subLevelModel))

    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        const val ID = "id"
        const val NAME = "name"
        const val SUB_NIVEL = "subNivel"

        fun fromJson(data: String): LevelModel = Gson().fromJson(data, LevelModel::class.java)

    }
}