package com.example.comusenias.presentation.component.bottomBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.comusenias.domain.models.bottomNavigation.BottomBarItem
import com.example.comusenias.domain.models.bottomNavigation.getBottomBarItems
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size24
import com.example.comusenias.presentation.ui.theme.size30
import com.example.comusenias.presentation.ui.theme.SIZE50

@Composable
fun ShowBottomBar(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE50.dp)
            .shadow(4.dp),
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        getBottomBarItems().forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        imageVector = getBottomBarImage(
                            currentRoute = currentRoute,
                            item = item
                        ),
                        contentDescription = item.route,
                        modifier = if (currentRoute == item.route) Modifier.size(size30.dp) else Modifier.size(
                            size24.dp
                        ),
                        tint = if (currentRoute == item.route) primaryColorApp else Color.Black
                    )
                },
                onClick = {
                    navigateTo(navController, item)
                },
            )
        }
    }
}

@Composable
fun GetBottomBarTitle(
    currentRoute: String?,
    item: BottomBarItem
) {
    if (currentRoute == item.route) Text(EMPTY_STRING) else Text(item.title)
}

@Composable
private fun getBottomBarImage(
    currentRoute: String?,
    item: BottomBarItem
) = if (currentRoute == item.route)
    ImageVector.vectorResource(id = item.selectedIcon)
else ImageVector.vectorResource(
    id = item.unselectedIcon
)

private fun navigateTo(
    navController: NavHostController,
    screen: BottomBarItem
) {
    navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
