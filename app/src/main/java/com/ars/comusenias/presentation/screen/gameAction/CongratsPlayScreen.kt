package com.ars.comusenias.presentation.screen.gameAction

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getChildrenProfileViewModel
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.component.gameAction.CongratsContent
import com.ars.comusenias.presentation.component.home.StatusGame
import com.ars.comusenias.presentation.component.home.getAllSubLevels
import com.ars.comusenias.presentation.extensions.validation.getChoicesSelected
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.CONTINUE
import com.ars.comusenias.presentation.ui.theme.SIZE30

@Composable
fun CongratsPlayScreen(navController: NavHostController, modifier: Modifier) {
    CongratsPlayView(navController = navController, modifier)
}

@Composable
fun CongratsPlayView(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current
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

                // Limpia el estado y recrea la actividad después de navegar
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)

                setStatusBySubLevel()
                getChildrenProfileViewModel.updateLevel()
               // getLevelViewModel.onClear()


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
    val allSubLevels = getAllSubLevels()
    val subLevelSelected = getLevelViewModel.subLevelSelected
    val currentIndex = allSubLevels.indexOfFirst { it.name == subLevelSelected }

    if (currentIndex != -1) {
        val subLevel = allSubLevels[currentIndex]
        val nextSubLevelIndex = currentIndex + 1

        subLevel.isCompleted = if (getChoicesSelected(getLevelViewModel)) StatusGame.COMPLETED else StatusGame.IN_PROGRESS

        if (subLevel.isCompleted == StatusGame.COMPLETED) {
            subLevel.successes += 1
        } else {
            subLevel.failures += 1
        }

        if (nextSubLevelIndex < allSubLevels.size) {
            val nextSubLevel = allSubLevels[nextSubLevelIndex]
            if (nextSubLevel.isCompleted != StatusGame.COMPLETED) {
                nextSubLevel.isCompleted = StatusGame.IN_PROGRESS
            }
        }
    }
}

