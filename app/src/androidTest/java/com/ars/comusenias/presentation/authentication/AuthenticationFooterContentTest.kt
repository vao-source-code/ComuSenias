package com.ars.comusenias.presentation.authentication

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.component.login.AuthenticationFooterContent
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthenticationFooterContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AuthenticationFooterContent(
                textOne = "No tienes una cuenta",
                textTwo = "Regístrate"
            )
        }
    }

    @Test
    fun testAuthenticationFooterContent() {
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_CLICK_FOOTER).assertIsDisplayed()
    }

    @Test
    fun testAuthenticationFooterContentClickAction() {
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_CLICK_FOOTER).assertHasClickAction()
    }
}