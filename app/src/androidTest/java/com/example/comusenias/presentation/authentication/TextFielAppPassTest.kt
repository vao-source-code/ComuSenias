package com.example.comusenias.presentation.authentication

import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import org.junit.Rule
import org.junit.Test

class TextFielAppPassTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTextFieldAppPassIsHiden(){
        composeTestRule.setContent {
            TextFieldAppPassword(
                value = "",
                onValueChange = {},
                label = "Contraseña")
        }

        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN  + "true", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN  + "true", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN  + "false", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun testTextFieldAppPassNotIsHiden(){
        composeTestRule.setContent {
            TextFieldAppPassword(
                value = "",
                onValueChange = {},
                label = "Contraseña")
        }

        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN + "true", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN  + "true", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN  + "false", useUnmergedTree = true).assertIsDisplayed()
    }
    @Test
    fun testTextFieldAppPassHeight(){
        composeTestRule.setContent {
            TextFieldAppPassword(
                value = "",
                onValueChange = {},
                label = "Contraseña")
        }

        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP_PASS, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP_PASS, useUnmergedTree = true).assertHeightIsEqualTo(50.dp)
    }
}