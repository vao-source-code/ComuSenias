package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.example.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchLetter
import com.example.comusenias.presentation.component.home.ContentProgressBar
import com.example.comusenias.presentation.component.home.getLevelViewModel
import com.example.comusenias.presentation.extensions.validation.selectedOption
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.ERROR_RETRY_LEVEL
import com.example.comusenias.presentation.ui.theme.STEP_TWO
import com.example.comusenias.presentation.ui.theme.WHAT_LETTER_IS
import com.example.comusenias.presentation.view_model.LevelViewModel

@Composable
fun ChoseTheLetterPlayScreen(
    navController: NavHostController,
    level: String,
    subLevel: String,
    levelViewModel: LevelViewModel
) {
    getLevelViewModel.levelSelected = level
    getLevelViewModel.subLevelModel = subLevel
    val subLevelViewModel = getLevelViewModel.getSubLevelById(level, subLevel)
    val isButtonEnabled = remember { mutableStateOf(false) }
    val onMatchResult: (Boolean) -> Unit = {
        isButtonEnabled.value = it
    }

    when (levelViewModel.levelsResponse) {
        is Response.Loading -> {
            CircularProgressBar()
        }

        is Response.Error -> {
            ShowRetrySnackBar(text = ERROR_RETRY_LEVEL, true, onActionClick = {
                levelViewModel.getLevels()
            })
        }

        is Response.Success -> {
            ShowScreenChoseTheLetter(
                subLevelViewModel,
                isButtonEnabled,
                navController,
                level,
                onMatchResult,
            )
        }

        else -> {
            ContentProgressBar(null)
        }
    }
}

@Composable
private fun ShowScreenChoseTheLetter(
    subLevel: SubLevelModel?,
    isButtonEnabled: MutableState<Boolean>,
    navController: NavHostController,
    level: String,
    onMatchResult: (Boolean) -> Unit,
) {
    subLevel?.let {
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
                            level,
                            subLevel.name
                        )
                )
                selectedOption(it.name, getLevelViewModel)
            },
        ) {
            MatchLetter(
                singLetter = it.name,
                randomLetter = it.randomLetter,
                onMatchResult = onMatchResult,
            )
        }
    }
}