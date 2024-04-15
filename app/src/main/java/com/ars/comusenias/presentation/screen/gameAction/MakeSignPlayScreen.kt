package com.ars.comusenias.presentation.screen.gameAction

import PermissionCameraScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ars.comusenias.presentation.view_model.LevelViewModel

@Composable
fun MakeSignPlayScreen(
    navController: NavHostController,
    modifier: Modifier,
    levelViewModel: LevelViewModel
) {
    Box(modifier = modifier.fillMaxSize()) {
        PermissionCameraScreen(navController = navController)
    }
}