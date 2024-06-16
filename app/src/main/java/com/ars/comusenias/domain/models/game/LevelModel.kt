package com.ars.comusenias.domain.models.game

import com.ars.comusenias.presentation.component.home.StatusGame
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

data class LevelModel(
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var subLevelId: List<String> = listOf(),
    var subLevel: MutableList<SubLevelModel> = mutableListOf(),
    var isCompleted: StatusGame = StatusGame.IN_PROGRESS,
) {
    fun toJson(): String = Gson().toJson(LevelModel(id, name, subLevelId, subLevel))

    companion object {
        fun fromJson(data: String): LevelModel = Gson().fromJson(data, LevelModel::class.java)
    }
}

