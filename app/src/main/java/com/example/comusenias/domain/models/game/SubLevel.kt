package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame

data class SubLevel(
    var learnSign: String,
    var images: String,
    var isCompleted: StatusGame,
    var game: List<Game>
)
