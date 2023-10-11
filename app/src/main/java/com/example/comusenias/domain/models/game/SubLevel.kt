package com.example.comusenias.domain.models.game

import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class SubLevel(
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var learnSign: String = EMPTY_STRING,
    var imageSing: String = EMPTY_STRING,
    var idGame: String = EMPTY_STRING,
    var game: Game? = null,
    var idLevel: String = EMPTY_STRING,
    //TODO deberia traerse de la base de datos de usuario o simil estos datos
    var isCompleted: StatusGame = StatusGame.IN_PROGRESS,
) {

    fun toJson(): String = Gson().toJson(
        SubLevel(
            id = id,
            name = name,
            learnSign = learnSign,
            imageSing = if (imageSing != "") LibraryString.encodeURL(imageSing) else "",
            idGame = idGame,
            game = game,
            idLevel = idLevel,
            isCompleted = isCompleted
        )
    )

    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        const val ID_LEVEL = "idLevel"
        fun fromJson(data: String): SubLevel = Gson().fromJson(data, SubLevel::class.java)

    }

}
