package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.example.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.example.comusenias.presentation.component.gameAction.ContentLetterType
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.ERROR_RETRY_LEVEL
import com.example.comusenias.presentation.ui.theme.LETS_GO
import com.example.comusenias.presentation.ui.theme.STEP_ONE
import com.example.comusenias.presentation.view_model.LevelViewModel

@Composable
fun LearnSignScreen(
    navController: NavHostController,
    level: String,
    subLevel: String,
    levelViewModel: LevelViewModel
) {
    if (getLevelViewModel.subLevelModelSelected.name != subLevel) {
        levelViewModel.getSubLevelById(level, subLevel)
    }
    getLevelViewModel.choiceOfOption.clear()
    getLevelViewModel.subLevelModelSelected.let {
        GameAction(
            if (!it.esVideo) it.image else it.imageOnly,
            title = LETS_GO,
            titleButton = CONTINUE,
            currentSteps = STEP_ONE,
            navController = navController,
            clickButton = {
                navController.navigate(
                    AppScreen
                        .ChoseTheLetterPlayScreen
                        .createRoute(level, it.name)
                )
            },
        ) {
            ContentLetterType(letter = it.name)
        }
    }


}/*@Composable
fun LearnSignScreen(
    navController: NavHostController,
    level: String,
    subLevel: String,
    levelViewModel: LevelViewModel
) {

    val subLevelViewModel = levelViewModel.getSubLevelById(level, subLevel)
    getLevelViewModel.choiceOfOption.clear()

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
            subLevelViewModel.let {
                GameAction(
                    if (!it.esVideo) it.image else it.imageOnly,
                    title = LETS_GO,
                    titleButton = CONTINUE,
                    currentSteps = STEP_ONE,
                    navController = navController,
                    clickButton = {
                        navController.navigate(
                            AppScreen
                                .ChoseTheLetterPlayScreen
                                .createRoute(level, it.name)
                        )
                    },
                ) {
                    ContentLetterType(letter = it.name)
                }
            }
        }

        else -> {
            CircularProgressBar()
        }
    }
}*/