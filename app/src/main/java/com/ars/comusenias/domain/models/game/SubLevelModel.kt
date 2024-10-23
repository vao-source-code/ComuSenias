package com.ars.comusenias.domain.models.game

import com.ars.comusenias.domain.library.LibraryString
import com.ars.comusenias.presentation.component.home.StatusGame
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class SubLevelModel(
    var idLevel: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var image: String = EMPTY_STRING,
    var imageOnly: String = EMPTY_STRING,
    var randomLetter: String = EMPTY_STRING,
    var randomImage: String = EMPTY_STRING,
    var randomImageOnly: String = EMPTY_STRING,
    var randomImageClean: String = EMPTY_STRING,
    var imageClean: String = EMPTY_STRING,
    var isCompleted: StatusGame = StatusGame.BLOCKED,
    var esVideo: Boolean = false,
    var failures: Int = 0,
    var successes: Int = 0,
    var isFirst: Boolean = false,
) {
    fun toJson(): String = Gson().toJson(
        SubLevelModel(
            idLevel,
            name,
            image = if (image != EMPTY_STRING) LibraryString.encodeURL(image) else EMPTY_STRING,
            imageOnly = if (imageOnly != EMPTY_STRING) LibraryString.encodeURL(imageOnly) else EMPTY_STRING,
            randomLetter =randomLetter,
            randomImage = randomImage,
            randomImageOnly =  randomImageOnly,
            isCompleted =isCompleted,
            esVideo = esVideo,
            failures = failures,
            successes = successes,
            isFirst = isFirst,
            imageClean = if (imageClean != EMPTY_STRING) LibraryString.encodeURL(imageClean) else EMPTY_STRING,
            randomImageClean = if (randomImageClean != EMPTY_STRING) LibraryString.encodeURL(randomImageClean) else EMPTY_STRING,
        )
    )



    companion object {
        /*-----------------------FIELDS ---------------------------------------------------------*/
        fun fromJson(data: String): SubLevelModel = Gson().fromJson(data, SubLevelModel::class.java)

    }
}
