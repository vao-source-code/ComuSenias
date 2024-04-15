package com.ars.comusenias.domain.models.letter

import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.FIELD_IMAGES
import com.ars.comusenias.presentation.ui.theme.ID
import com.ars.comusenias.presentation.ui.theme.LETTER
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class LetterModel(
    val id: String = EMPTY_STRING,
    var letter: String = EMPTY_STRING,
    val image: String = EMPTY_STRING,
) {

    fun toJson(): String = Gson().toJson(
        LetterModel(
            id,
            letter,
            if (image != EMPTY_STRING) URLEncoder.encode(
                image,
                StandardCharsets.UTF_8.toString()
            ) else EMPTY_STRING,
        )
    )

    companion object {
        fun fromJson(data: String): LetterModel = Gson().fromJson(data, LetterModel::class.java)

        const val FIELD_ID = ID
        const val FIELD_LETTER = LETTER
        const val FIELD_IMAGE = FIELD_IMAGES
    }
}