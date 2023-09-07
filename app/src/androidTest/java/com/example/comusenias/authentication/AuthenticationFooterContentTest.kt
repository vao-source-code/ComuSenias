package com.example.comusenias.authentication

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.comusenias.presentation.component.defaults.AuthenticationFooterContent
import org.junit.Rule
import org.junit.Test

class AuthenticationFooterContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAuthenticationFooterContent(){
        composeTestRule.setContent {
            AuthenticationFooterContent()
        }
        composeTestRule.onNodeWithTag("text click action").assertIsDisplayed()
    }
    @Test
    fun testAuthenticationFooterContentClickAction(){
        composeTestRule.setContent {
            AuthenticationFooterContent()
        }
        composeTestRule.onNodeWithTag("text click action").assertHasClickAction()
    }
}