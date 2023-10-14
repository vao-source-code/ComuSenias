package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.users.KidsMock
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchLetter
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.STEP_TWO
import com.example.comusenias.presentation.ui.theme.WHAT_LETTER_IS

@Composable
fun ChoseTheLetterPlayScreen(navController: NavHostController, subLevel: String) {
    val isButtonEnabled = remember { mutableStateOf(false) }
    val onMatchResult: (Boolean) -> Unit = {
        isButtonEnabled.value = true
    }
    KidsMock.instance.subLevel.forEach {
        if (it.name == subLevel) {
            GameAction(
                imageSign = it.imageOnly,
                title = WHAT_LETTER_IS,
                titleButton = CONTINUE,
                enabledButton = isButtonEnabled.value,
                currentSteps = STEP_TWO,
                navController = navController,
                clickButton = {
                    navController.navigate(
                        AppScreen
                            .ChoseTheSignPlayScreen
                            .createRoute(
                                subLevel
                            )
                    )
                },
            ) {
                MatchLetter(
                    singLetter = it.name,
                    randomLetter = it.randomLetter,
                    responseMatchLetter = onMatchResult
                )
            }
        }
    }
}