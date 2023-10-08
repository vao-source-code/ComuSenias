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
import com.example.comusenias.presentation.ui.theme.FINISH
import com.example.comusenias.presentation.ui.theme.STEP_FOUR
import com.example.comusenias.presentation.ui.theme.THE_SIGN_IS

@Composable
fun InterpretationStatusScreen(navController: NavHostController, modifier: Modifier) {
    val isButtonEnabled = remember { mutableStateOf(false) }
    val response: (Boolean) -> Unit = { it ->
            isButtonEnabled.value = it
    }

    GameAction(
        imageSign = R.drawable.hand_sign,
        enabledButton = isButtonEnabled.value,
        title = THE_SIGN_IS,
        titleButton = FINISH,
        currentSteps = STEP_FOUR,
        navController = navController,
        clickButton = { navController.navigate(AppScreen.CongratsPlayScreen.route) },
    ) {
        SignInterpretationStatus(response = response)
    }
}