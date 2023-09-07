package com.example.comusenias.authentication

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.defaults.ButtonApp
import org.junit.Rule
import org.junit.Test

class ButtonAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testButtonApp(){
        composeTestRule.setContent {
            ButtonApp(titleButton = "Iniciar sesión")
        }
        composeTestRule.onNodeWithTag("button").assertIsDisplayed()
    }

    @Test
    fun testButtonAppHasClickAction(){
        composeTestRule.setContent {
            ButtonApp(
                titleButton = "Iniciar sesión",
            )
        }
        composeTestRule.onNodeWithTag("button").assertHasClickAction()
    }
    @Test
    fun testButtonAppPerfomClick(){
        composeTestRule.setContent {
            ButtonApp(
                titleButton = "Iniciar sesión"
            )
        }
        composeTestRule.onNodeWithTag("button").performClick()
    }

    @Test
    fun testButtonAppHeigth(){
        composeTestRule.setContent {
            ButtonApp(
                titleButton = "Iniciar sesión"
            )
        }
        composeTestRule.onNodeWithTag("button").assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun testButtonAppTitle(){
        composeTestRule.setContent {
            ButtonApp(
                titleButton = "Iniciar sesión"
            )
        }
        composeTestRule.onNodeWithText("Iniciar sesión").assertIsDisplayed()
    }


}