package com.ars.comusenias.domain.models.bottomNavigation

import com.ars.comusenias.R
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.LEAR_TEXT
import com.ars.comusenias.presentation.ui.theme.PROFILE_TEXT
import com.ars.comusenias.presentation.ui.theme.SUPPORT_TEXT

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
            selectedIcon = R.drawable.icon_home,
            unselectedIcon = R.drawable.icon_home,
        ), BottomBarItem(
            route = AppScreen.ChildrenProfileScreen.route,
            title = PROFILE_TEXT,
            selectedIcon = R.drawable.icon_profile,
            unselectedIcon = R.drawable.icon_profile,
        ),
        BottomBarItem(
            route = AppScreen.SupportScreen.route,
            title = SUPPORT_TEXT,
            selectedIcon = R.drawable.icon_support,
            unselectedIcon = R.drawable.icon_support,
        )
    )

}