package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.models.room.SubLevelEntity
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.example.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.example.comusenias.presentation.ui.theme.ERROR_RETRY_LEVEL
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.size14
import com.example.comusenias.presentation.view_model.LevelViewModel
import com.example.comusenias.presentation.view_model.SubLevelViewModel

lateinit var getLevelViewModel: LevelViewModel
lateinit var getSubLevelViewModel: SubLevelViewModel

@Composable
fun ContentHome(
    navController: NavController,
    levelViewModel: LevelViewModel
) {
    val level = levelViewModel.levels
    getLevelViewModel = levelViewModel
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
            ShowLazyColumn(level, navController, levelViewModel)
        }

        else -> {
            ContentProgressBar()
        }
    }
}

@Composable
private fun ShowLazyColumn(
    level: List<LevelModel>,
    navController: NavController,
    levelViewModel: LevelViewModel
) {
    val subLevelViewModel: SubLevelViewModel = hiltViewModel()
    getSubLevelViewModel = subLevelViewModel

    val subLevelsEntity = getSubLevelViewModel.subLevels.collectAsState(
        initial = emptyList()
    )

    getSubLevelViewModel.insertSubLevel(createSubLevelEntity(getSubLevel(level)))

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = size14.dp,
                end = size14.dp,
            )
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(SIZE1.dp)
    ) {
        item {
            ContentProgressBar()
        }
        item {
            ContentLevel(level[0].name)
        }
        items(
            items = getSubLevel(level),
        ) { subLevel ->
            ContentCardGame(
                status = getStatusSubLevel(subLevelsEntity.value, subLevel),
                level = level[0].id,
                subLevel = subLevel,
                navController = navController
            )
        }
        item {
            CardGameCheckPoint()
        }
    }
}

/**
 * Ddetermina el estado de juego para un subnivel específico.
 *
 * @param sublevelsEntity La lista de todos los subniveles disponibles en el juego. Cada subnivel es una entidad que contiene su id y estado actual.
 * @param sublevel El subnivel para el que se debe determinar el estado de juego.
 *
 * @return El estado de juego para el subnivel especificado. Puede ser uno de los siguientes:
 * - StatusGame.IN_PROGRESS: Si el subnivel es el próximo subnivel después del último subnivel completado.
 * - StatusGame.BLOCKED: Si el subnivel está después del próximo subnivel después del último subnivel completado.
 * - El estado actual del subnivel: Si el subnivel no está después del último subnivel completado.
 * - StatusGame.BLOCKED: Si el subnivel no se encuentra en la lista de subniveles.
 */
fun getStatusSubLevel(sublevelsEntity: List<SubLevelEntity>, sublevel: SubLevelModel): StatusGame {
    val statusList = sublevelsEntity.map { it.status }
    val lastCompletedIndex = statusList.indexOfLast { it == StatusGame.COMPLETED }
    val currentIndex = sublevelsEntity.indexOfFirst { it.idSubLevel == sublevel.name }

    if (statusList.all { it == StatusGame.BLOCKED }) {
        sublevelsEntity.getOrNull(0)?.status = StatusGame.IN_PROGRESS
    }

    return when {
        lastCompletedIndex != -1 && currentIndex == lastCompletedIndex + 1 -> StatusGame.IN_PROGRESS
        lastCompletedIndex != -1 && currentIndex > lastCompletedIndex + 1 -> StatusGame.BLOCKED
        else -> sublevelsEntity.getOrNull(currentIndex)?.status ?: StatusGame.BLOCKED
    }
}

/**
 * Crea entidades de subnivel a partir de una lista de modelos de subnivel.
 *
 * @param subLevel la lista de modelos de subnivel.
 * @return una lista de entidades de subnivel, donde cada entidad corresponde a un modelo de la lista original.
 */
fun createSubLevelEntity(subLevel: List<SubLevelModel>): List<SubLevelEntity> =
    subLevel.map {
        SubLevelEntity(
            idSubLevel = it.name,
            status = it.isCompleted
        )
    }

/**
 * Obtiene todos los subniveles de una lista de modelos de nivel.
 *
 * @param level la lista de modelos de nivel.
 * @return una lista de modelos de subnivel, que incluye todos los subniveles de todos los niveles en la lista original.
 */
fun getSubLevel(level: List<LevelModel>): List<SubLevelModel> =
    level.flatMap { it.subLevel }