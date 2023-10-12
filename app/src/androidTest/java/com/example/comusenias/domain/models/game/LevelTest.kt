package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame
import org.junit.Assert.assertEquals
import org.junit.Test

class LevelTest {


    @Test
    fun shouldCreateALevelObjectWithAllProperties() {
        val levelName = "Level 1"
        val isCompleted = StatusGame.IN_PROGRESS
        val learnSign = listOf("A", "B", "C")
        val subLevel = listOf(
            SubLevelModel(
                name = "SubLevel 1",
                image = learnSign[0],
                isCompleted = StatusGame.COMPLETED,
                imageOnly = "imageOnly",
                randomLetter = "A",
                randomImage = "randomImage"
            ),
            SubLevelModel(
                name = "SubLevel 2",
                image = learnSign[1],
                imageOnly = "imageOnly",
                randomLetter = "A",
                randomImage = "randomImage",
                isCompleted = StatusGame.COMPLETED
            )
        )
        val images = listOf("a.png", "b.png", "c.png")

        val levelModel = LevelModel(
            name = levelName,
            isCompleted = isCompleted,
            subLevel = subLevel,
        )

        assertEquals(levelName, levelModel.name)
        assertEquals(isCompleted, levelModel.isCompleted)
        assertEquals(subLevel, levelModel.subLevel)
    }
}