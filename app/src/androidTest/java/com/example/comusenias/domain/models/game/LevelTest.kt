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
        val subLevel = listOf(SubLevel("1", "SubLevel 1", StatusGame.COMPLETED, listOf()), SubLevel("2", "SubLevel 2", StatusGame.IN_PROGRESS, listOf()))
        val images = listOf("a.png", "b.png", "c.png")

        val levelModel = Level(
                name = levelName,
                isCompleted = isCompleted,
                learnSign = learnSign,
                subLevel = subLevel,
                images = images
        )

        assertEquals(levelName, levelModel.name)
        assertEquals(isCompleted, levelModel.isCompleted)
        assertEquals(learnSign, levelModel.learnSign)
        assertEquals(subLevel, levelModel.subLevel)
        assertEquals(images, levelModel.images)
    }
}