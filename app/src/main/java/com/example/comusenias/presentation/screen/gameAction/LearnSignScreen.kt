package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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
                    imageSign = it.image,
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
}