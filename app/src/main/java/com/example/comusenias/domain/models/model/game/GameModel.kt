package com.example.comusenias.domain.models.model.game

import com.example.comusenias.constants.AlphabetConstants
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class GameModel(
    var id: String = EMPTY_STRING,
    var idLevel: String = EMPTY_STRING,
    var idSublevel: String = EMPTY_STRING,
    var imageSing: String = EMPTY_STRING,
    var learSing: String = EMPTY_STRING,
    var randomSing: String = AlphabetConstants.getRandomLetter().toString()
) {

    fun toJson(): String = Gson().toJson(
        GameModel(
            id = id,
            idLevel = idLevel,
            idSublevel = idSublevel,
            imageSing = if (imageSing != "") LibraryString.encodeURL(imageSing) else "",
            learSing = learSing,
            randomSing = randomSing,
        )
    )

    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        const val ID = "id"
        const val IMAGE_SING = "imageSing"
        const val LEAR_SING = "learSing"
        const val RANDOM_SING = "randomSing"
        const val ID_LEVEL = "idLevel"
        const val ID_SUBLEVEL = "idSublevel"

        fun fromJson(data: String): GameModel = Gson().fromJson(data, GameModel::class.java)
    }

}