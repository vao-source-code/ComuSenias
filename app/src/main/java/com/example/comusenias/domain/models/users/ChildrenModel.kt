package com.example.comusenias.domain.models.users

import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.observation.Observation
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

data class ChildrenModel(
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var tel: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var image: String? = EMPTY_STRING,
    var date: String = EMPTY_STRING,
    var idSpecialist: String = EMPTY_STRING,
    var specialist: SpecialistModel? = null,
    var levelActual: Int = 0,
    var subLevelActual: Int = 0,
    var isPremium: Boolean = false,
    var levels: List<LevelModel> = listOf(),
    var observation: List<Observation>? = listOf()
)