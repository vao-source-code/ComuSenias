package com.example.comusenias.domain.models.users

import com.example.comusenias.domain.models.game.Level
import com.example.comusenias.domain.models.observation.Observation

data class ChildrenModel(
    var userModel: UserModel,
    var date: String,
    var specialist: SpecialistModel?,
    var isPremium: Boolean = false,
    var levels: List<Level>,
    var observation: List<Observation>?
)