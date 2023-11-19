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
        val response = testSignInterpretationStatus(Status.CORRECT)
        assert(response)
    }

    @Test
    fun testWhenSignInterpretationStatusReturnFalse() {
        val response = testSignInterpretationStatus(Status.INCORRECT)
        assert(!response)
    }

    private fun testSignInterpretationStatus(status: Status) : Boolean {
        var response = false

        composeTestRule.setContent {
            SignInterpretationStatus(status = status) {
                response = it
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(status.name).assertExists()

        return response
    }

    @Test
    fun testWhenCrossfadeIconIsCorrect() {
        testCrossfadeIcon(Status.CORRECT)
    }

    @Test
    fun testWhenCrossfadeIconIsInCorrect() {
        testCrossfadeIcon(Status.INCORRECT)
    }

    @Test
    fun testWhenCrossfadeIconIsInLoading() {
        testCrossfadeIcon(Status.LOADING)
    }

    private fun testCrossfadeIcon(status: Status) {
        composeTestRule.setContent {
            CrossfadeIcon(status) { }
        }

        composeTestRule.onNodeWithTag(status.name).assertExists()
    }
}