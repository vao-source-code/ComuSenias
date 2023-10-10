package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchSign
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.STEP_TREE
import com.example.comusenias.presentation.ui.theme.WHAT_SIGN_IS

@Composable
fun ChoseTheSignPlayScreen(navController: NavHostController, modifier: Modifier) {
    val isButtonEnabled = remember { mutableStateOf(true) }

    val onMatchResult: (Boolean) -> Unit = {
        isButtonEnabled.value = true
    }

    val sign = Sign(imageResource = R.drawable.letra_a_solo, letter = "a")
    val randomSign = Sign(imageResource = R.drawable.sign_o, letter = "o")

    GameAction(
        imageSign = R.drawable.letra_a_solo,
        letterSign = sign.letter,
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
            letterCompare = sign.letter,
            responseMatchLetter = onMatchResult
        )
    }
}

data class Sign(
    val imageResource: Int,
    val letter: String
)