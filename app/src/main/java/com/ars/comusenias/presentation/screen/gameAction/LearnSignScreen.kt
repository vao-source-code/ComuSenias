package com.ars.comusenias.presentation.screen.gameAction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.ars.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.ars.comusenias.presentation.component.gameAction.ContentLetterType
import com.ars.comusenias.presentation.component.gameAction.GameAction
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.CONTINUE
import com.ars.comusenias.presentation.ui.theme.ERROR_RETRY_LEVEL
import com.ars.comusenias.presentation.ui.theme.LETS_GO
import com.ars.comusenias.presentation.ui.theme.STEP_ONE
import com.ars.comusenias.presentation.view_model.LevelViewModel

@Composable
fun LearnSignScreen(
    navController: NavHostController,
    level: String,
    subLevel: String,
    levelViewModel: LevelViewModel
) {


    Image(
        painter = painterResource(id = R.drawable.background_game), // Reemplaza con tu VectorDrawable
        contentDescription = null, // Descripción accesible opcional
        modifier = Modifier.fillMaxSize(), // Ajusta el modificador según tu necesidad
        contentScale = ContentScale.Crop // Ajusta el escalado para adaptarse al fondo
    )

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
}