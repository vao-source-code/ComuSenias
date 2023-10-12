package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame

data class LevelModel(
    var id: String = "",
    var name: String = "",
    var subLevel: List<SubLevelModel>,
    var isCompleted: StatusGame = StatusGame.IN_PROGRESS,
)

