package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.activities.MainActivity.Companion.getChildrenProfileViewModel
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.gameAction.CongratsContent
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.component.home.getAllSubLevels
import com.example.comusenias.presentation.extensions.validation.getChoicesSelected
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.SIZE30

@Composable
fun CongratsPlayScreen(navController: NavHostController, modifier: Modifier) {
    CongratsPlayView(navController = navController, modifier)
}

@Composable
fun CongratsPlayView(navController: NavHostController, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SIZE30.dp)
            .testTag(TestTag.TAG_INDICATOR),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CongratsContent()
        ButtonApp(
            titleButton = CONTINUE,
            onClickButton = {
                navController.navigate(AppScreen.HomeScreen.route)
                setStatusBySubLevel()
                getChildrenProfileViewModel.updateLevel()
            }
        )
    }
    DisposableEffect(Unit) {
        onDispose { }
    }
}

/**
 * Actualiza el estado del subnivel en el ViewModel basándose en los choices seleccionados en el LevelViewModel.
 * Si los choices seleccionados son correctos, el estado se establece en COMPLETED.
 * En caso contrario, el estado se establece en IN_PROGRESS.
 */
fun setStatusBySubLevel() {
    getAllSubLevels().find { it.name == getLevelViewModel.subLevelSelected }?.let { subLevel ->
        subLevel.isCompleted =
            if (getChoicesSelected(getLevelViewModel)) StatusGame.COMPLETED else StatusGame.IN_PROGRESS
    }
    getChildrenProfileViewModel.userData.levels.forEach {
        it.subLevel.forEach { subLevel ->
            if (subLevel.name == getLevelViewModel.subLevelSelected) {
                subLevel.isCompleted =
                    if (getChoicesSelected(getLevelViewModel)) StatusGame.COMPLETED else StatusGame.IN_PROGRESS
                if (getChoicesSelected(getLevelViewModel)) {
                    subLevel.successes += 1
                } else {
                    subLevel.failures += 1
                }
            }
        }
    }
}
