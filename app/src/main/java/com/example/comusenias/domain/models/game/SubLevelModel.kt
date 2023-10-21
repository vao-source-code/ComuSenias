package com.example.comusenias.domain.models.game

import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class SubLevelModel(
    var idLevel: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    val image: String = EMPTY_STRING,
    val imageOnly: String = EMPTY_STRING,
    var randomLetter: String = EMPTY_STRING,
    var randomImage: String = EMPTY_STRING,
    var isCompleted: StatusGame = StatusGame.BLOCKED,
) {
    fun toJson(): String = Gson().toJson(
        SubLevelModel(
            idLevel,
            name,
           image = if (image != EMPTY_STRING) LibraryString.encodeURL(image) else EMPTY_STRING,
           imageOnly = if (imageOnly != EMPTY_STRING) LibraryString.encodeURL(imageOnly) else EMPTY_STRING,
            randomLetter,
            randomImage,
            isCompleted

        )
    )

    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        fun fromJson(data: String): SubLevelModel = Gson().fromJson(data, SubLevelModel::class.java)

    }
}
