package com.ars.comusenias.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ars.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.ars.comusenias.presentation.component.home.ContentHome
import com.ars.comusenias.presentation.component.home.TopBarHome
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.SIZE3
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel


/**
 * Pantalla principal del usuario que va a jugar el juego
 */


@Composable
fun HomeScreen(
    navController: NavController,
    levelViewModel: LevelViewModel,
    childrenModel: ChildrenProfileViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = SIZE3.dp) {
                TopBarHome(
                    name = childrenModel.userData.name,
                    image = childrenModel.userData.image,
                    onClickNotification = { navController.navigate(AppScreen.NotificationScreen.route) },
                    onclickSupport = { navController.navigate(AppScreen.SupportScreen.route) },
                    onclickProfile = { navController.navigate(AppScreen.ChildrenProfileScreen.route) }
                )
            }
        },
        bottomBar = {
            ShowBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ContentHome(
                navController = navController,
                levelViewModel = levelViewModel,
                childrenModel = childrenModel
            )
        }
    }
}
