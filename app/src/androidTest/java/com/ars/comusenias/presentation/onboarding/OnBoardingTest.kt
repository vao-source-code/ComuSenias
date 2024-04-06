package com.ars.comusenias.presentation.onboarding

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.component.onboarding.BottomSection
import com.ars.comusenias.presentation.component.onboarding.Indicator
import com.ars.comusenias.presentation.component.onboarding.TopSection
import com.ars.comusenias.presentation.screen.onboarding.OnBoardingScreen
import org.junit.Rule
import org.junit.Test

class OnBoardingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWhenIsClickInTheFloatingButtonShouldBeShowTheNextOnBoardingItem() {
        testOnBoardingItemsNavigation(0, 1)
    }

    @Test
    fun testWhenIndicatorIsSelectWidthIsEqualToSizeIndicatorSelect() {
        indicatorWidth(true, 16.dp)
    }

    @Test
    fun testWhenIndicatorIsDiSelectWidthIsEqualToSizeIndicatorDiSelect() {
        indicatorWidth(false, 12.dp)
    }

    @Test
    fun testTopSectionWhenExistTextSkip() {
        val text = "Saltar"
        composeTestRule.setContent {
            val navController = rememberNavController()
            TopSection(navController = navController)
        }
        composeTestRule.onNodeWithText(text).assertExists()
    }

    @Test
    fun testFloatingButtonIsClickAction() {
        composeTestRule.setContent {
            BottomSection(size = 3, index = 1) {}
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_NEXT).assertIsDisplayed().assertHasClickAction()
    }

    private fun testOnBoardingItemsNavigation(pageOne: Int, pageTwo: Int) {
        composeTestRule.setContent {
            val navController = rememberNavController()
            OnBoardingScreen(navController = navController, modifier = Modifier)
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_ONBOARDING_ITEM + pageOne).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTag.TAG_BUTTON_NEXT).performClick()
        composeTestRule.onNodeWithTag(TestTag.TAG_ONBOARDING_ITEM + pageTwo).assertIsDisplayed()
    }

    private fun indicatorWidth(isSelected: Boolean, expectedWidth: Dp) {
        composeTestRule.setContent {
            Indicator(isSelected = isSelected)
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_INDICATOR + isSelected).assertWidthIsEqualTo(expectedWidth)
    }
}