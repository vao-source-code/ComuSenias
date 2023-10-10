package com.example.comusenias.domain.models.model.game

import com.example.comusenias.domain.library.LibraryString
import com.google.gson.Gson


data class SubLevelModel(
        var id: String = "",
        var name: String = "",
        var idLevel: String = "",
        var idGame: String = "",
        var image: String = "",
        var letter: String = "",
) {
    fun toJson(): String = Gson().toJson(SubLevelModel(
            id = id,
            name = name,
            idLevel = idLevel,
            idGame = idGame,
            image = if (image != "") LibraryString.encodeURL(image) else "",
            letter = letter,
    ))

    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        const val ID = "id"
        const val NAME = "name"
        const val IMAGE = "image"
        const val ID_LEVEL = "idLevel"
        const val LETTER = "letter"
        fun fromJson(data: String): SubLevelModel = Gson().fromJson(data, SubLevelModel::class.java)

    }


}
