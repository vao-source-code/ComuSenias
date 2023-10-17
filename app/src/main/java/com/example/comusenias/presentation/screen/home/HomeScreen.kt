package com.example.comusenias.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.example.comusenias.presentation.component.home.ContentHome
import com.example.comusenias.presentation.component.home.TopBarHome
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.size3
import com.example.comusenias.presentation.view_model.LevelViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    levelViewModel: LevelViewModel
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = size3.dp) {
                TopBarHome(onClick = { navController.navigate(AppScreen.NotificationScreen.route) })
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
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                levelViewModel
            )
        }
    }
}
