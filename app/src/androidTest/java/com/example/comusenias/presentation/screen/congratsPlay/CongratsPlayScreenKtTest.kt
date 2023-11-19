package com.example.comusenias.presentation.screen.congratsPlay

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.activities.MainActivity
import com.example.comusenias.presentation.activities.MainActivity.Companion.getChildrenProfileViewModel
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.screen.gameAction.CongratsPlayView
import com.example.comusenias.presentation.screen.gameAction.setStatusBySubLevel
import com.example.comusenias.presentation.view_model.ChildrenProfileViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CongratsPlayScreenKtTest {

    private val mockChildrenProfileViewModel = mockk<ChildrenProfileViewModel>()
    private lateinit var launchComposeTestRule: ActivityScenarioRule<MainActivity>

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getLevelViewModel = mockk()
        getChildrenProfileViewModel = mockChildrenProfileViewModel
        val level = LevelModel("1", "1")
        every { getChildrenProfileViewModel.userData.levels } returns listOf(level)
        every { getLevelViewModel.subLevelSelected } returns "1"
        every { getLevelViewModel.choiceOfOption } returns mutableListOf(true, true, true)
        launchComposeTestRule = ActivityScenarioRule(MainActivity::class.java)

    }

    @Test
    fun whenAllChoicesSelected() {
        val subLevel = SubLevelModel("1", "1", "1", "1", "1", "1")

        setStatusBySubLevel()

        assertEquals(StatusGame.BLOCKED, subLevel.isCompleted)
    }

    @Test
    fun whenClickInButtonContinue_verifyNavigateHomeScreen() {
        val CONTINUE_TAG = "Continuar"
        val navController = mockk<NavHostController>(relaxed = true)
        composeTestRule.setContent {
            CongratsPlayView(navController, Modifier)
        }
        every { mockChildrenProfileViewModel.userData.levels } returns listOf(
            LevelModel("1", "1")
        )
        every { mockChildrenProfileViewModel.updateLevel() } returns mockk()

        composeTestRule.onNodeWithText(CONTINUE_TAG).assertExists()
        composeTestRule.onNodeWithText(CONTINUE_TAG).performClick()

        verify { navController.navigate(AppScreen.HomeScreen.route) }
    }
}