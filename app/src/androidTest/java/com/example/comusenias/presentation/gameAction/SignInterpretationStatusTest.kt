package com.example.comusenias.presentation.gameAction

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.comusenias.presentation.component.gameAction.CrossfadeIcon
import com.example.comusenias.presentation.component.gameAction.SignInterpretationStatus
import com.example.comusenias.presentation.component.gameAction.Status
import org.junit.Rule
import org.junit.Test

class SignInterpretationStatusTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWhenSignInterpretationStatusReturnTrue() {
        var response = false

        composeTestRule.setContent {
            SignInterpretationStatus(status = Status.CORRECT) {
                response = it
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(Status.CORRECT.name).assertExists()
        assert(response)
    }

    @Test
    fun testWhenSignInterpretationStatusReturnFalse() {
        var response = true

        composeTestRule.setContent {
            SignInterpretationStatus(status = Status.INCORRECT) {
                response = it
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(Status.INCORRECT.name).assertExists()
        assert(!response)
    }

    @Test
    fun testWhenCrossfadeIconIsCorrect() {
        val status = Status.CORRECT

        composeTestRule.setContent {
            CrossfadeIcon(status) { }
        }

        composeTestRule.onNodeWithTag(status.name).assertExists()
    }

    @Test
    fun testWhenCrossfadeIconIsInCorrect() {
        val status = Status.INCORRECT

        composeTestRule.setContent {
            CrossfadeIcon(status) { }
        }

        composeTestRule.onNodeWithTag(status.name).assertExists()
    }

    @Test
    fun testWhenCrossfadeIconIsInLoading() {
        val status = Status.LOADING

        composeTestRule.setContent {
            CrossfadeIcon(status) { }
        }

        composeTestRule.onNodeWithTag(status.name).assertExists()
    }
}