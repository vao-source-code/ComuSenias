package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.gameAction.ContentLetterType
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun LearnSignScreen(navController: NavHostController, modifier: Modifier) {
    GameAction(
        imageSign = R.drawable.letra_a,
        title = "Comencemos",
        titleButton = "Continuar",
        clickButton = { navController.navigate(AppScreen.ChoseTheSignPlayScreen.route) },
    ) {
        ContentLetterType(letter = "A")
    }
}