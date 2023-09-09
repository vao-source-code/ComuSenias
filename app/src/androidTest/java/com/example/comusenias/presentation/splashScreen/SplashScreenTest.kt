package com.example.comusenias.presentation.splashScreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testSplashScreen() {
        composeTestRule.setContent {
            SplashScreenContent()
        }
        composeTestRule.onNodeWithText("comuseñas", ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun asd() {
        assertEquals(1, 1)
    }

}