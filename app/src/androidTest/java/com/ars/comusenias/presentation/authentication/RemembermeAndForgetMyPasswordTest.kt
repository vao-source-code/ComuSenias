package com.ars.comusenias.presentation.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.component.defaults.app.CheckBoxApp
import com.ars.comusenias.presentation.component.login.ForgetMyPass
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
        composeTestRule.onNodeWithTag(TestTag.TAG_FORGET_MY_PASS).assertTextEquals("Olvide mi contraseña")
    }

    @Test
    fun testForgetMyPassHasClickAction(){
        composeTestRule.setContent {
            ForgetMyPass()
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_FORGET_MY_PASS).assertHasClickAction()
    }

    @Test
    fun testCheckBoxAppIsOff() {
        var isCheckedValue = true

        composeTestRule.setContent {
            CheckBoxApp(
                isChecked = mutableStateOf(isCheckedValue)
            ) {
                isCheckedValue = it
            }
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_CHECKBOX_APP).performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_CHECKBOX_APP).assertIsOff()
    }

    @Test
    fun testCheckBoxAppIsOn(){
        var isCheckedValue = false

        composeTestRule.setContent {
            CheckBoxApp(
                isChecked = mutableStateOf(isCheckedValue)
            ) {
                isCheckedValue = it
            }
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_CHECKBOX_APP).performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_CHECKBOX_APP).assertIsOn()
    }
}