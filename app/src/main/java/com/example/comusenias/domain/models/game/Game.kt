package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame

data class Game(
    var learnSign: String,
    var imageSign: String,
    var randomSign: String?,
    var randomLetter: String?,
    var numberOfError: Int?,
    var numberOfCorrect: Int?,
    var isCompleted: StatusGame
)
