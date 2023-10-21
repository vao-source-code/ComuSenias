package com.example.comusenias.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import org.junit.Rule
import org.junit.Test

class CircularProgressBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun circularProgressBarIsDisplayed() {
        composeTestRule.setContent {
            CircularProgressBar()
        }
        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }
}