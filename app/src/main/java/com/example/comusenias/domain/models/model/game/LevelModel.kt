package com.example.comusenias.domain.models.model.game

import com.google.gson.Gson

data class LevelModel (
    var id: String = "",
    var name: String = "",
    var sublevels: List<String> = ArrayList()
){

    fun toJson(): String = Gson().toJson(LevelModel(
        id,
        name,

    ))

    companion object {
        fun fromJson(data: String): LevelModel = Gson().fromJson(data, LevelModel::class.java)
    }
}