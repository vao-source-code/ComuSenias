package com.example.comusenias.domain.models

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Letters(
    val id: String = "",
    var letter: String = "",
    val image: String = "",
){

    fun toJson(): String = Gson().toJson(Letters(
        id,
        letter,

        if (image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else "",
    ))

    companion object {
        fun fromJson(data: String): Letters = Gson().fromJson(data, Letters::class.java)

        const val FIELD_ID = "id"
        const val FIELD_LETTER = "letter"
        const val FIELD_IMAGE = "image"

    }

}