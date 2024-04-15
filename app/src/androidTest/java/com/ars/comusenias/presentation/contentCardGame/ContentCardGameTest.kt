package com.ars.comusenias.presentation.contentCardGame

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.presentation.component.home.ContentCardGame
import com.ars.comusenias.presentation.component.home.StatusGame
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ContentCardGameTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val mockNavController: NavHostController = Mockito.mock(NavHostController::class.java)
    private val subLevel = SubLevelModel("", "1", R.drawable.letra_a.toString(), "1", "1", "1")

    @Test
    fun testContentCardGameCompleted() {
        testContentCardGameWithStatus(StatusGame.COMPLETED)
    }

    @Test
    fun testContentCardGameProgress() {
        testContentCardGameWithStatus(StatusGame.IN_PROGRESS)
    }

    @Test
    fun testContentCardGameBlocked() {
        testContentCardGameWithStatus(StatusGame.BLOCKED)
    }

    private fun testContentCardGameWithStatus(status: StatusGame) {
        composeTestRule.setContent {
            ContentCardGame(
                status,
                "",
                subLevel,
                mockNavController
            )
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_CONTENT_CARD_GAME + status.name)
            .assertExists()
    }
}