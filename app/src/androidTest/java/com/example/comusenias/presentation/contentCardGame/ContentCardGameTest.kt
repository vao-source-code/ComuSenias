package com.example.comusenias.presentation.contentCardGame

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.home.ContentCardGame
import com.example.comusenias.presentation.component.home.StatusGame
import org.junit.Rule
import org.junit.Test

class ContentCardGameTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testContentCardGameCompleted() {
        composeTestRule.setContent {
            ContentCardGame(StatusGame.COMPLETED)
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_CONTENT_CARD_GAME + StatusGame.COMPLETED.name)
            .assertExists()
    }

    @Test
    fun testContentCardGameProgress() {
        composeTestRule.setContent {
            ContentCardGame(StatusGame.IN_PROGRESS)
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_CONTENT_CARD_GAME + StatusGame.IN_PROGRESS.name)
            .assertExists()
    }

    @Test
    fun testContentCardGameBlocked() {
        composeTestRule.setContent {
            ContentCardGame(StatusGame.BLOCKED)
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_CONTENT_CARD_GAME + StatusGame.BLOCKED.name)
            .assertExists()
    }
}


