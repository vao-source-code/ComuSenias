package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame

data class Level(
    var levelName: String,
    var isCompleted: StatusGame,
    var learnSign: List<String>,
    var subLevel: List<SubLevel>,
    var images: List<String>
)
