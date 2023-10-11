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
        val subLevel= listOf(
            SubLevel(id= "1", name = "SubLevel 1", learnSign= learnSign[0], idGame= "gameid_A" , isCompleted=  StatusGame.COMPLETED, game = Game()),
            SubLevel(id= "2", name = "SubLevel 2", learnSign= learnSign[1], idGame= "gameid_B" , isCompleted=  StatusGame.COMPLETED, game = Game())
        )
        val images = listOf("a.png", "b.png", "c.png")

        val levelModel = Level(
            name = levelName,
            isCompleted = isCompleted,
            subLevelModel = subLevel as ArrayList<SubLevel>,
        )

        assertEquals(levelName, levelModel.name)
        assertEquals(isCompleted, levelModel.isCompleted)
        assertEquals(subLevel as ArrayList<SubLevel>, levelModel.subLevel)
    }
}