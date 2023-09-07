package com.example.comusenias.authentication

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.comusenias.presentation.component.login.ForgetMyPass
import org.junit.Rule
import org.junit.Test

class RemembermeAndForgetMyPasswordTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testForgetMyPass(){
        composeTestRule.setContent {
            ForgetMyPass()
        }
        composeTestRule.onNodeWithText("Olvide mi contraseña").assertIsDisplayed()
    }

    @Test
    fun testForgetMyPassHasClickAction(){
        composeTestRule.setContent {
            ForgetMyPass()
        }
        composeTestRule.onNodeWithTag("ForgetMyPass").assertHasClickAction()
    }
}