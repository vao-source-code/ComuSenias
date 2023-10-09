package com.example.comusenias.domain.models.model.game

import com.example.comusenias.domain.library.LibraryString
import com.google.gson.Gson


data class SubLevelModel(
        val id: String = "",
        val name: String = "",
        val image: String = "",
        val idLevel: String = "",
        val letter: String = "",
) {
    fun toJson(): String = Gson().toJson(SubLevelModel(
            id = id,
            name = name,
            idLevel = idLevel,
            letter = letter,
            image = if (image != "") LibraryString.encodeURL(image) else ""
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
