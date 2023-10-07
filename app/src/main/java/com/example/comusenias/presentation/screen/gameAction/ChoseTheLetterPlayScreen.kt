package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchLetter
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun ChoseTheLetterPlayScreen(navController: NavHostController) {
    var letter by remember { mutableStateOf("e") }
    val letterRandom = remember { mutableStateOf(letter) }
    val isButtonEnabled = remember { mutableStateOf(false) }
    val stepTwo = 2
    val onMatchResult: (Boolean) -> Unit = {
        isButtonEnabled.value = true
    }

    GameAction(
        imageSign = R.drawable.letra_a_solo,
        title = "¿Qué letra es?",
        titleButton = "Continuar",
        enabledButton = isButtonEnabled.value,
        currentSteps = stepTwo,
        navController = navController,
        clickButton = { navController.navigate(AppScreen.ChoseTheSignPlayScreen.route) },
    ) {
        MatchLetter(
            singLetter = "A",
            randomLetter = letterRandom.value,
            responseMatchLetter = onMatchResult
        )
    }
}