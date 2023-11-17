package com.example.comusenias.presentation.authentication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TextFieldInputAppTest {
    private val value = mutableStateOf("fabian@gmail.com")
    private val label = "Correo electrónico"
    private val icon = Icons.Default.Email

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            TextFieldApp(
                value = value.value,
                onValueChange = { value.value = it },
                label = label,
                icon = icon
            )
        }
    }

    @Test
    fun textFieldDisplaysCorrectValue() {
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP).assertTextEquals(value.value)
    }

    @Test
    fun textFieldHasCorrectHeight() {
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP).assertHeightIsEqualTo(56.dp)
    }
}