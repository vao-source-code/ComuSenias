package com.example.comusenias.authentication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.defaults.TextFieldApp
import org.junit.Rule
import org.junit.Test

class TextFieldInputAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInputTexFieldValue(){
        composeTestRule.setContent {
            TextFieldApp(
                value = "fabian@gmail.com",
                onValueChange = { } ,
                label = "Correo electrónico" ,
                icon = Icons.Default.Email
            )
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP).assertTextEquals("fabian@gmail.com")
    }
    @Test
    fun testInputTexFieldHeight(){
        composeTestRule.setContent {
            TextFieldApp(
                value = "fabian@gmail.com",
                onValueChange = { } ,
                label = "Correo electrónico" ,
                icon = Icons.Default.Email
            )
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_TEXT_FIELD_APP).assertHeightIsEqualTo(50.dp)
    }
}