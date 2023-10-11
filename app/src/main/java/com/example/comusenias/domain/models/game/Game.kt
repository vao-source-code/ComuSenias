package com.example.comusenias.domain.models.game

import com.example.comusenias.constants.AlphabetConstants
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class Game(
    var id: String = EMPTY_STRING,
    var learnSign: String = EMPTY_STRING,
    var imageSign: String  = EMPTY_STRING,
    var randomSign: String  = EMPTY_STRING,
    var randomLetter: String? = AlphabetConstants.getRandomLetter().toString(),
    //TODO deberia traerse de la base de datos de usuario o simil estos datos
    var numberOfError: Int? = 0,
    var numberOfCorrect: Int? = 0,
    var isCompleted: StatusGame = StatusGame.IN_PROGRESS,
){

    fun toJson(): String = Gson().toJson(
        Game(
            learnSign = learnSign,
            imageSign = if (imageSign != "") LibraryString.encodeURL(imageSign) else "",
            randomSign = if (randomSign != "") LibraryString.encodeURL(randomSign) else "",
            randomLetter = randomLetter,
            numberOfError = numberOfError,
            numberOfCorrect = numberOfCorrect,
            isCompleted = isCompleted
        )
    )

    companion object {
        fun fromJson(data: String): Game = Gson().fromJson(data, Game::class.java)
    }
}
