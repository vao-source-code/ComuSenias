package com.ars.comusenias.domain.models.bottomNavigation

import com.ars.comusenias.R
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.LEAR_TEXT
import com.ars.comusenias.presentation.ui.theme.PROFILE_TEXT

data class BottomBarItem(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
)

fun getBottomBarItems(): List<BottomBarItem> {
    return listOf(
        BottomBarItem(
            route = AppScreen.HomeScreen.route,
            title = LEAR_TEXT,
            selectedIcon = R.drawable.book_fechado,
            unselectedIcon = R.drawable.book_open,
        ), BottomBarItem(
            route = AppScreen.ChildrenProfileScreen.route,
            title = PROFILE_TEXT,
            selectedIcon = R.drawable.girl,
            unselectedIcon = R.drawable.children,
        )
    )

    /*
    BottomBarItem(
            route = AppScreen.PremiumScreen.route,
            title = PREMIUM_TEXT,
            selectedIcon = R.drawable.workspace_premium,
            unselectedIcon = R.drawable.workspace_premium,
        )
     */
}