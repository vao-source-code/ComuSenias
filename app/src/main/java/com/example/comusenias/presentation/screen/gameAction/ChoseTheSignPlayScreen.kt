package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchLetter
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun ChoseTheSignPlayScreen(navController: NavHostController, modifier: Modifier) {

    var enabled by remember { mutableStateOf(false) }
    val isButtonEnabled = remember { mutableStateOf(enabled) }

    GameAction(
        imageSign = R.drawable.letra_a_solo,
        title = "¿Que letra es?",
        titleButton = "Continuar",
        enabledButton = isButtonEnabled.value,
        clickButton = { navController.navigate(AppScreen.MakeSignPlayScreen.route) },
    ) {
        MatchLetter(singLetter = "A", randomLetter = "U") {
            isButtonEnabled.value = true
        }
    }
}
