package com.ars.comusenias.presentation.authentication

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ButtonAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ButtonApp(titleButton = "Iniciar sesión")
        }
    }

    @Test
    fun testButtonApp() {
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_APP).assertIsDisplayed()
    }

    @Test
    fun testButtonAppHasClickAction() {
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_APP).assertHasClickAction()
    }

    @Test
    fun testButtonAppPerfomClick() {
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_APP).performClick()
    }

    @Test
    fun testButtonAppHeigth() {
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_APP).assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun testButtonAppTitle() {
        composeTestRule.onNodeWithText("Iniciar sesión").assertIsDisplayed()
    }
}
