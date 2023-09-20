package com.example.comusenias.domain.models.bottomNavigation

import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.LEAR_TEXT
import com.example.comusenias.presentation.ui.theme.PREMIUM_TEXT
import com.example.comusenias.presentation.ui.theme.PROFILE_TEXT

data class BottomBarItem(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
)

fun getBottomBarItems(): List<BottomBarItem> {
    return listOf(
        //TODO hay que agregar los menu correctos para cada pantalla
        BottomBarItem(
            route = AppScreen.HomeScreen.route,
            title = LEAR_TEXT,
            selectedIcon = R.drawable.book_fechado,
            unselectedIcon = R.drawable.book_open,
        ), BottomBarItem(
            route = AppScreen.PremiumScreen.route,
            title = PREMIUM_TEXT,
            selectedIcon = R.drawable.workspace_premium,
            unselectedIcon = R.drawable.workspace_premium,
        ), BottomBarItem(
            route = AppScreen.ProfileScreen.route,
            title = PROFILE_TEXT,
            selectedIcon = R.drawable.girl,
            unselectedIcon = R.drawable.children,
        )
    )
}