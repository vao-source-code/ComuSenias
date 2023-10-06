package com.example.comusenias.domain.models.games

data class Level(
    val name: String,
    val level: String,
    val isComplete: Boolean,
    val subLevel: List<SubLevel>,
)

data class SubLevel(
    val learnSign: String,
    val image: String,
    val randomSign: String,
    val isCompleted: Boolean,
)
