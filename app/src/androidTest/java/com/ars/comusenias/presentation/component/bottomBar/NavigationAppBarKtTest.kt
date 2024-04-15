package com.ars.comusenias.presentation.component.bottomBar

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.ars.comusenias.domain.models.bottomNavigation.BottomBarItem
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import org.junit.Rule
import org.junit.Test

class NavigationAppBarKtTest {
    private val homeItem = BottomBarItem("home", "Home", 0, 0)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBottomBarTitleIsEmptyWhenRouteIsHome() {
        composeTestRule.setContent {
            GetBottomBarTitle(homeItem.route, homeItem)
        }

        composeTestRule.onNodeWithText(EMPTY_STRING).assertTextEquals(EMPTY_STRING)
    }

    @Test
    fun testBottomBarTitleIsHomeWhenRouteIsNotHome() {
        val currentRoute = "Screen"

        composeTestRule.setContent {
            GetBottomBarTitle(currentRoute, homeItem)
        }

        composeTestRule.onNodeWithText(homeItem.title).assertTextEquals(homeItem.title)
    }
}