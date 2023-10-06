package com.example.comusenias.domain.models.user

import com.example.comusenias.domain.models.games.History
import com.example.comusenias.domain.models.games.Level
import java.util.Date

data class Kid(
    var nationalIdentity: String,
    var date: Date,
    var specialist: Specialist?,
    var isPremium: Boolean = false,
    var levelActual: Level,
    var levelCompleted: List<Level>,
    var history: List<History>,
    var observation: String?,
    var levelComplete: List<Level>?,
) : User() {

    fun deleteSpecialist() {
        specialist = null
    }

    fun asd(specialist: Specialist?): Boolean {
        return specialist?.let {
            this.specialist = it
            true
        } ?: false
    }
}

