package com.example.comusenias.domain.models.game

import org.junit.Assert.assertEquals
import org.junit.Test

class LevelTest {

    @Test
    fun shouldCreateALevelObjectWithAllProperties() {
        val levelName = "Level 1"
        val subLevel = mutableListOf(
            SubLevelModel("1", "1", "1", "SubLevel 1", "1"),
            SubLevelModel("1", "1", "1", "SubLevel 1", "1"),
        )

        val levelModel = LevelModel(
            id = "1",
            name = levelName,
            subLevel = subLevel,
        )

        assertEquals(levelName, levelModel.name)
        assertEquals(subLevel, levelModel.subLevel)
    }
}