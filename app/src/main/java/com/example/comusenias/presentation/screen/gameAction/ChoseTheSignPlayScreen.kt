package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.users.KidsMock
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchSign
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.STEP_TREE
import com.example.comusenias.presentation.ui.theme.WHAT_SIGN_IS

@Composable
fun ChoseTheSignPlayScreen(navController: NavHostController, subLevel: String) {
    val isButtonEnabled = remember { mutableStateOf(true) }
    val onMatchResult: (Boolean) -> Unit = {
        isButtonEnabled.value = true
    }

    KidsMock.instance.subLevel.forEach {
        if (it.name == subLevel) {
            val sign = Sign(imageResource = it.imageOnly.toInt(), letter = it.name)
            val randomSign = Sign(imageResource = it.randomImage.toInt(), letter = it.randomLetter)
            GameAction(
                imageSign = it.image,
                letterSign = it.name,
                title = WHAT_SIGN_IS,
                titleButton = CONTINUE,
                enabledButton = isButtonEnabled.value,
                currentSteps = STEP_TREE,
                navController = navController,
                clickButton = { navController.navigate(AppScreen.MakeSignPlayScreen.route) },
            ) {
                MatchSign(
                    sign = sign,
                    randomSign = randomSign,
                    letterCompare = it.name,
                    responseMatchLetter = onMatchResult
                )
            }
        }
    }
}

data class Sign(
    val imageResource: Int,
    val letter: String
)