package com.example.comusenias.app

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import org.junit.Rule
import org.junit.Test

class ShowRetrySnackBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun snackbarIsDisplayedWhenSnackbarVisibleIsTrue() {
        composeTestRule.setContent {
            ShowRetrySnackBar("Error occurred", snackbarVisible = true) {
            }
        }

        composeTestRule.onNodeWithText("REINTENTAR").assertExists()
        composeTestRule.onNodeWithText("Error occurred").assertExists()
    }

    @Test
    fun snackbarIsNotDisplayedWhenSnackbarVisibleIsFalse() {
        composeTestRule.setContent {
            ShowRetrySnackBar("Error occurred", false, {})
        }

        composeTestRule.onNodeWithText("REINTENTAR").assertDoesNotExist()
        composeTestRule.onNodeWithText("Error occurred").assertDoesNotExist()
    }
}
