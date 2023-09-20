package com.example.comusenias.presentation.onboarding

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.onboarding.BottomSection
import com.example.comusenias.presentation.component.onboarding.Indicator
import com.example.comusenias.presentation.component.onboarding.TopSection
import com.example.comusenias.presentation.screen.onboarding.OnBoardingScreen
import org.junit.Rule
import org.junit.Test

class OnBoardingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWhenIsClickInTheFloatingButtonShouldBeShowTheNextOnBardingItem(){
        val pageOne = 0
        val pageTwo = 1
        composeTestRule.setContent {
            val navController = rememberNavController()
            OnBoardingScreen(navController = navController, modifier = Modifier )
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_ONBOARDING_ITEM + pageOne ).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_NEXT).performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_ONBOARDING_ITEM + pageTwo).assertIsDisplayed()
    }

    @Test
    fun testWhenIndicatorIsSelectWidthIsEqualToSizeIndicatorSelect(){
        val sizeIndicatorSelect = 16.dp
        val isSelect = true
        composeTestRule.setContent {
           Indicator(isSelected = isSelect)
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_INDICATOR + isSelect ).assertWidthIsEqualTo(sizeIndicatorSelect)
    }

    @Test
    fun testWhenIndicatorIsDiSelectWidthIsEqualToSizeIndicatorDiSelect(){
        val sizeIndicatorDiSelect = 13.dp
        val isSelect = false
        composeTestRule.setContent {
            Indicator(isSelected = isSelect)
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_INDICATOR + isSelect ).assertWidthIsEqualTo(sizeIndicatorDiSelect)
    }

    @Test
    fun testTopSectionWhenExistTextSkip(){
        val text = "Saltar"
        composeTestRule.setContent {
            val navController = rememberNavController()
            TopSection(navController = navController)
        }
        composeTestRule.onNodeWithText(text).assertExists()
    }

    @Test
    fun testFloatingButtonIsClickAction(){
        composeTestRule.setContent {
            BottomSection(size = 3, index = 1 ) {
            }
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_NEXT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_NEXT).assertHasClickAction()
    }
}