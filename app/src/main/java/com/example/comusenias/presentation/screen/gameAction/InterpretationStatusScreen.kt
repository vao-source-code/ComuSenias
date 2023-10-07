package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.SignInterpretationStatus
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun InterpretationStatusScreen(navController: NavHostController, modifier: Modifier) {
    val isButtonEnabled = remember { mutableStateOf(false) }
    val stepFour = 5
    val response: (Boolean) -> Unit = { it ->
            isButtonEnabled.value = it
    }

    GameAction(
        imageSign = R.drawable.letra_a_solo,
        enabledButton = isButtonEnabled.value,
        title = "La seña es...",
        titleButton = "Finalizar",
        currentSteps = stepFour,
        navController = navController,
        clickButton = { navController.navigate(AppScreen.CongratsPlayScreen.route) },
    ) {
        SignInterpretationStatus(response = response)
    }
}