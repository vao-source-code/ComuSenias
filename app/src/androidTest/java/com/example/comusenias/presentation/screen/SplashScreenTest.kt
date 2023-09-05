package com.example.comusenias.presentation.screen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.comusenias.presentation.splashScreen.SplashScreenContent
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
            composeTestRule.onNodeWithTag("ComuSeñas" ).assertExists()
        }

    @Test
    fun asd() {
        assertEquals(1, 1)
    }

}