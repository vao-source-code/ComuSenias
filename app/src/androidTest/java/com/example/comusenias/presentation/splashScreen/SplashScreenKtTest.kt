package com.example.comusenias.presentation.splashScreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class SplashScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    val navController: NavHostController = Mockito.mock(NavHostController::class.java)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SplashScreen(navController)
        }
    }

    @Test
    fun whenCallSplashScreen_existBoxSplash() {
        composeTestRule.onNodeWithTag("boxSplashScreen").assertIsDisplayed()
        composeTestRule.onNodeWithTag("columnSplashScreen").assertIsDisplayed()
        composeTestRule.onNodeWithTag("tagImage").assertIsDisplayed()
    }
}