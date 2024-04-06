package com.ars.comusenias.presentation.component.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ContentHomeKtTest {
    private val subLevels1 = mutableListOf(
        SubLevelModel("1", isCompleted = StatusGame.COMPLETED),
        SubLevelModel("2", isCompleted = StatusGame.BLOCKED)
    )
    private val subLevels2 = mutableListOf(
        SubLevelModel("1", isCompleted = StatusGame.BLOCKED),
        SubLevelModel("2", isCompleted = StatusGame.BLOCKED)
    )

    private val levels1 = listOf(
        LevelModel("1", subLevel = subLevels1),
        LevelModel("2", subLevel = subLevels1)
    )
    private val levels2 = listOf(
        LevelModel("1", subLevel = subLevels2),
        LevelModel("2", subLevel = subLevels2)
    )

    @MockK
    private lateinit var getChildrenProfileViewModel: ChildrenProfileViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        MainActivity.getLevelViewModel = mockk()
        MainActivity.getChildrenProfileViewModel = getChildrenProfileViewModel
    }

    @Test
    fun testAllLevelsRetrievedCorrectly() {
        every { getChildrenProfileViewModel.userData.levels } returns levels1

        val result = getAllLevels()

        assertEquals(levels1, result)
    }

    @Test
    fun testAllSubLevelsRetrievedCorrectly() {
        every { getChildrenProfileViewModel.userData.levels } returns levels1

        val result = getAllSubLevels()

        assertEquals(subLevels1 + subLevels1, result)
    }

    @Test
    fun testSubLevelStatusIsCompleted() {
        every { getChildrenProfileViewModel.userData.levels } returns levels1

        val result = getStatusSubLevel(SubLevelModel("2", isCompleted = StatusGame.BLOCKED))

        assertEquals(StatusGame.COMPLETED, result)
    }

    @Test
    fun testSubLevelStatusIsInProgress() {
        every { getChildrenProfileViewModel.userData.levels } returns levels2

        val result = getStatusSubLevel(SubLevelModel("2", isCompleted = StatusGame.IN_PROGRESS))

        assertEquals(StatusGame.IN_PROGRESS, result)
    }

    @Test
    fun whenResponseIsLoading_showCircularProgressBar() {
        MainActivity.getLevelViewModel = mockk()
        every { MainActivity.getLevelViewModel.levelsResponse } returns Response.Loading
        every { getChildrenProfileViewModel.userData.levels } returns listOf()

        composeTestRule.setContent {
            ContentHome(mockk(), MainActivity.getLevelViewModel, getChildrenProfileViewModel)
        }

        composeTestRule.onNodeWithContentDescription("CircularProgressBar").assertIsDisplayed()
    }

    @Test
    fun contentHomeDisplaysShowRetrySnackBarOnErrorState() {
        MainActivity.getLevelViewModel = mockk()
        every { MainActivity.getLevelViewModel.levelsResponse } returns Response.Error(Exception())

        composeTestRule.setContent {
            ContentHome(mockk(), MainActivity.getLevelViewModel, getChildrenProfileViewModel)
        }

        composeTestRule.onNodeWithText("Error al cargar el nivel").assertIsDisplayed()
    }
}