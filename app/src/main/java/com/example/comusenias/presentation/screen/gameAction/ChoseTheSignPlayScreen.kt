package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchSign
import com.example.comusenias.presentation.component.home.ContentProgressBar
import com.example.comusenias.presentation.component.home.getLevelViewModel
import com.example.comusenias.presentation.extensions.validation.selectedOption
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.ERROR_RETRY_SUB_LEVEL
import com.example.comusenias.presentation.ui.theme.STEP_TREE
import com.example.comusenias.presentation.ui.theme.WHAT_SIGN_IS
import com.example.comusenias.presentation.view_model.LevelViewModel

@Composable
fun ChoseTheSignPlayScreen(
    navController: NavHostController,
    level: String,
    subLevel: String,
    levelViewModel: LevelViewModel
) {
    val subLevelViewModel = levelViewModel.getSubLevelById(level, subLevel)

    val isButtonEnabled = remember { mutableStateOf(true) }
    val onMatchResult: (Boolean) -> Unit = {
        isButtonEnabled.value = it
    }

    when (levelViewModel.levelsResponse) {
        is Response.Loading -> {
            CircularProgressBar()
        }

        is Response.Error -> {
            Snackbar {
                Text(text = ERROR_RETRY_SUB_LEVEL)
            }
        }

        is Response.Success -> {
            ShowChoseTheSign(subLevelViewModel, isButtonEnabled, navController, onMatchResult)
        }

        else -> {
            ContentProgressBar(null)
        }
    }
}

@Composable
private fun ShowChoseTheSign(
    subLevel: SubLevelModel?,
    isButtonEnabled: MutableState<Boolean>,
    navController: NavHostController,
    onMatchResult: (Boolean) -> Unit
) {
    subLevel?.let {
        val sign = Sign(imageResource = it.imageOnly, letter = it.name)
        val randomSign = Sign(imageResource = it.randomImageOnly, letter = it.randomLetter)
        GameAction(
            imageSign = it.image,
            letterSign = it.name,
            title = WHAT_SIGN_IS,
            titleButton = CONTINUE,
            enabledButton = isButtonEnabled.value,
            currentSteps = STEP_TREE,
            navController = navController,
            clickButton = {
                navController.navigate(AppScreen.MakeSignPlayScreen.route)
                selectedOption(
                    it.imageOnly,
                    getLevelViewModel
                )
            },
        ) {
            MatchSign(
                sign = sign,
                randomSign = randomSign,
                onMatchResult = onMatchResult
            )
        }
    }
}

data class Sign(
    val imageResource: String,
    val letter: String
)