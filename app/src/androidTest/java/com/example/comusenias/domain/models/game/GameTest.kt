package com.example.comusenias.domain.models.game

import com.example.comusenias.presentation.component.home.StatusGame
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GameTest {

    @Test
    fun shouldCreateAGameObjectWithAllProperties() {
        val game = Game(
                learnSign = "A",
                imageSign = "a.png",
                randomSign = "A",
                randomLetter = "a",
                numberOfError = 0,
                numberOfCorrect = 0,
                isCompleted = StatusGame.IN_PROGRESS
        )

        assertEquals("A", game.learnSign)
        assertEquals("a.png", game.imageSign)
        assertEquals("A", game.randomSign)
        assertEquals("a", game.randomLetter)
        assertEquals(0, game.numberOfError)
        assertEquals(0, game.numberOfCorrect)
        assertEquals(StatusGame.IN_PROGRESS, game.isCompleted)
    }
}