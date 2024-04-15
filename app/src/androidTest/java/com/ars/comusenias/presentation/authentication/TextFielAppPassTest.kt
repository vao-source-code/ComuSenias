package com.ars.comusenias.presentation.authentication

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TextFielAppPassTest {
    private val label = "Contraseña"
    private val value = mutableStateOf("")

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TextFieldAppPassword(
                value = value.value,
                onValueChange = { value.value = it },
                label = label
            )
        }
    }

    @Test
    fun testTextFieldAppPassIsHiden() {
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN + "true", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN + "true", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN + "false", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun testTextFieldAppPassNotIsHiden() {
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN + "true", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN + "true", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_ICON_IS_HIDEN + "false", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun testTextFieldAppPassHeight() {
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP_PASS, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP_PASS, useUnmergedTree = true)
            .assertHeightIsEqualTo(50.dp)
    }
}