package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun MakeSignPlayScreen(navController: NavHostController, modifier: Modifier) {
    GameAction(
        imageSign = R.drawable.letra_a_solo,
        title = "Hace la seña A",
        titleButton = "Interpretar seña",
        clickButton = { navController.navigate(AppScreen.CongratsPlayScreen.route) }
    )
}