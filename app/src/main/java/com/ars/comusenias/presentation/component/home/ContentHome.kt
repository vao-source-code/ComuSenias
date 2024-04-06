package com.ars.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getChildrenProfileViewModel
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.ars.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.ars.comusenias.presentation.ui.theme.ERROR_RETRY_LEVEL
import com.ars.comusenias.presentation.ui.theme.SIZE1
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE5
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel



@Composable
fun ContentHome(
    navController: NavController,
    levelViewModel: LevelViewModel,
    childrenModel: ChildrenProfileViewModel
) {
    getLevelViewModel = levelViewModel
    getChildrenProfileViewModel = childrenModel

    when (getLevelViewModel.levelsResponse) {
        is Response.Loading -> {
            CircularProgressBar()
        }

        is Response.Error -> {
            ShowRetrySnackBar(text = ERROR_RETRY_LEVEL, true, onActionClick = {
                levelViewModel.getLevels()
            })
        }

        is Response.Success -> {
            ShowLazyColumn(getAllLevels(), navController)
        }

        else -> {
            CircularProgressBar()
        }
    }
}

@Composable
private fun ShowLazyColumn(
    levels: List<LevelModel>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = SIZE14.dp,
                end = SIZE14.dp,
            )
            .background(White),
        verticalArrangement = spacedBy(SIZE1.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(SIZE5.dp))
            ContentProgressBar(levels = getAllLevels(), getAllSubLevels())
        }
        items(
            items = levels,
        ) { level ->
            ContentLevel(level)

            level.subLevel.forEach { subLevel ->
                ContentCardGame(
                    status = getStatusSubLevel(subLevel),
                    level = level.id,
                    subLevel = subLevel,
                    navController = navController
                )
            }
            Spacer(modifier = Modifier.height(SIZE5.dp))
            CardGameCheckPoint()
        }
    }
}

/**
 * Ddetermina el estado de juego para un subnivel específico.
 *
 * @param subLevel La lista de todos los subniveles disponibles en el juego. Cada subnivel es una entidad que contiene su id y estado actual.
 *
 * @return El estado de juego para el subnivel especificado. Puede ser uno de los siguientes:
 * - StatusGame.IN_PROGRESS: Si el subnivel es el próximo subnivel después del último subnivel completado.
 * - StatusGame.BLOCKED: Si el subnivel está después del próximo subnivel después del último subnivel completado.
 * - El estado actual del subnivel: Si el subnivel no está después del último subnivel completado.
 * - StatusGame.BLOCKED: Si el subnivel no se encuentra en la lista de subniveles.
 */

fun getStatusSubLevel(
    subLevel: SubLevelModel
): StatusGame {
    val statusList = getAllSubLevels().map { it.isCompleted }
    val lastCompletedIndex = statusList.indexOfLast { it == StatusGame.COMPLETED }
    val currentIndex = getAllSubLevels().indexOfFirst { it.name == subLevel.name }

    if (statusList.all { it == StatusGame.BLOCKED }) {
        getAllSubLevels().getOrNull(0)?.isCompleted = StatusGame.IN_PROGRESS
    }


    return when {
        lastCompletedIndex != -1 && currentIndex == lastCompletedIndex + 1 -> StatusGame.IN_PROGRESS
        lastCompletedIndex != -1 && currentIndex > lastCompletedIndex + 1 -> StatusGame.BLOCKED
        else -> getAllSubLevels().getOrNull(currentIndex)?.isCompleted ?: StatusGame.BLOCKED
    }


}

fun getAllLevels(): List<LevelModel> {
    return getChildrenProfileViewModel.userData.levels
}

fun getAllSubLevels(): List<SubLevelModel> {
    return getChildrenProfileViewModel.userData.levels.flatMap { it.subLevel }
}