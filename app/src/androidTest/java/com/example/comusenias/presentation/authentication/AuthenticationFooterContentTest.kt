package com.example.comusenias.presentation.authentication

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import org.junit.Rule
import org.junit.Test

class AuthenticationFooterContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAuthenticationFooterContent(){
        composeTestRule.setContent {
            AuthenticationFooterContent(
                textOne = "No tienes una cuenta",
                textTwo = "Regístrate",
            )
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_CLICK_FOOTER).assertIsDisplayed()
    }
    @Test
    fun testAuthenticationFooterContentClickAction(){
        composeTestRule.setContent {
            AuthenticationFooterContent(
                textOne = "No tienes una cuenta",
                textTwo = "Regístrate",

            )
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_CLICK_FOOTER).assertHasClickAction()
    }
}