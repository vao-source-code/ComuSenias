package com.example.comusenias.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.comusenias.presentation.component.defaults.CheckBoxApp
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
    fun testForgetMyPassText(){
        composeTestRule.setContent {
            ForgetMyPass()
        }
        composeTestRule.onNodeWithTag("ForgetMyPass").assertTextEquals("Olvide mi contraseña")
    }

    @Test
    fun testForgetMyPassHasClickAction(){
        composeTestRule.setContent {
            ForgetMyPass()
        }
        composeTestRule.onNodeWithTag("ForgetMyPass").assertHasClickAction()
    }

    @Test
    fun testCheckBoxAppIsOff(){
        composeTestRule.setContent {
            val isChecked = remember { mutableStateOf(false) }
            CheckBoxApp(isChecked = isChecked)
        }
        composeTestRule.onNodeWithTag("checkboxApp").assertIsOff()
    }

    @Test
    fun testCheckBoxAppIsOn(){
        composeTestRule.setContent {
            val isChecked = remember { mutableStateOf(true) }
            CheckBoxApp(isChecked = isChecked)
        }
        composeTestRule.onNodeWithTag("checkboxApp").assertIsOn()
    }
}