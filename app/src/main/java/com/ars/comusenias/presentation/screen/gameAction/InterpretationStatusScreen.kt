package com.ars.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.component.gameAction.GameAction
import com.ars.comusenias.presentation.component.gameAction.SignInterpretationStatus
import com.ars.comusenias.presentation.component.gameAction.Status
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.FINISH
import com.ars.comusenias.presentation.ui.theme.STEP_FOUR
import com.ars.comusenias.presentation.ui.theme.THE_SIGN_IS
import kotlinx.coroutines.delay

@Composable
fun InterpretationStatusScreen(
    navController: NavHostController, modifier: Modifier, path: String,
) {
    var status by remember { mutableStateOf(Status.LOADING) }
    val isButtonEnabled = remember { mutableStateOf(false) }
    val response: (Boolean) -> Unit = { it ->
        isButtonEnabled.value = it
    }

    LaunchedEffect(Unit) {
        delay(5000)
        status = if (getLevelViewModel.validateLetterCamera()) Status.CORRECT else Status.INCORRECT
        getLevelViewModel.choiceOfOption.add(status == Status.CORRECT)
    }
    GameAction(
        imageSign = if (getLevelViewModel.isVideo) getLevelViewModel.pathVideo else path,
        enabledButton = true,
        title = THE_SIGN_IS,
        titleButton = FINISH,
        currentSteps = STEP_FOUR,
        navController = navController,
        clickButton = { navController.navigate(AppScreen.CongratsPlayScreen.route) },
    ) {
        SignInterpretationStatus(
            status = status, response = response
        )
    }
}