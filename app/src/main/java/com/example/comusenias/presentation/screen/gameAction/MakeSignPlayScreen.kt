package com.example.comusenias.presentation.screen.gameAction

import PermissionCameraScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.view_model.LevelViewModel

@Composable
fun MakeSignPlayScreen(
    navController: NavHostController,
    modifier: Modifier,
    levelViewModel: LevelViewModel
) {
    PermissionCameraScreen(navController = navController)
}