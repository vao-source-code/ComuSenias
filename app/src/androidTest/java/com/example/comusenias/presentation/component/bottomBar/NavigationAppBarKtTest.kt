package com.example.comusenias.presentation.component.bottomBar

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.comusenias.domain.models.bottomNavigation.BottomBarItem
import org.junit.Rule
import org.junit.Test


class NavigationAppBarKtTest {

    private val EMPTY_STRING = ""

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenCurrentRouterIsEqualsToItemRoute_thenEmptyString() {
        val currentRoute = "home"
        val item = BottomBarItem("home", "Home", 0, 0)

        composeTestRule.setContent {
            GetBottomBarTitle(currentRoute, item)
        }

        composeTestRule.onNodeWithText(EMPTY_STRING).assertTextEquals(EMPTY_STRING)
    }

    @Test
    fun whenCurrentRouterIsNotEqualsToItemRoute_returnItemTitle() {
        val currentRoute = "Screen"
        val item = BottomBarItem("home", "Home", 0, 0)

        composeTestRule.setContent {
            GetBottomBarTitle(currentRoute, item)
        }

        composeTestRule.onNodeWithText(item.title).assertTextEquals(item.title)
    }
}