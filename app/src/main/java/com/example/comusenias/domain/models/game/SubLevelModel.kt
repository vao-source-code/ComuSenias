package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame

data class SubLevelModel(
    var name: String,
    val image: String,
    val imageOnly: String,
    var randomLetter: String,
    var randomImage: String,
    var isCompleted: StatusGame = StatusGame.BLOCKED,
)
