package com.example.comusenias.presentation.component.bottomBar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.comusenias.domain.models.bottomNavigation.BottomBarItem
import com.example.comusenias.domain.models.bottomNavigation.getBottomBarItem
import com.example.comusenias.presentation.ui.theme.SIZE70

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomNavigation(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SIZE70.dp),
                containerColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                getBottomBarItem().forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = getBottomBarImage(currentRoute, item),
                                contentDescription = item.route
                            )
                        },
                        label = { GetBottomBarTitle(currentRoute, item) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navigateTo(navController, item)
                        },
                    )
                }
            }
        }
    ) {
    }
}

@Composable
private fun GetBottomBarTitle(
    currentRoute: String?,
    item: BottomBarItem
) {
    if (currentRoute == item.route) Text("") else Text(item.title)
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