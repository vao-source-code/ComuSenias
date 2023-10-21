package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.users.KidsMock
import com.example.comusenias.presentation.component.gameAction.ContentLetterType
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.LETS_GO
import com.example.comusenias.presentation.ui.theme.STEP_ONE

@Composable
fun LearnSignScreen(
    navController: NavHostController,
    modifier: Modifier,
    subLevel: String
) {
    KidsMock.instance.subLevel.forEach {
        if (it.name == subLevel) {
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
                            .createRoute(subLevel)
                    )
                },
            ) {
                ContentLetterType(letter = it.name)
            }
        }
    }
}