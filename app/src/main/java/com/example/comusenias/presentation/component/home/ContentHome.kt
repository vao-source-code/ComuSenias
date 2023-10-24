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
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.models.room.SubLevelEntity
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.example.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.example.comusenias.presentation.ui.theme.ERROR_RETRY_LEVEL
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.size14
import com.example.comusenias.presentation.view_model.LevelViewModel
import com.example.comusenias.presentation.view_model.SubLevelRoomViewModel

lateinit var getLevelViewModel: LevelViewModel
lateinit var getSubLevelViewModel: SubLevelRoomViewModel

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
    val adjustLevel = levelViewModel.adjustIsCompleted(level)
    val subLevelViewModel: SubLevelRoomViewModel = hiltViewModel()
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
            items = getSubLevel(adjustLevel),
        ) { subLevel ->
            ContentCardGame(
                status = getStatusForRoom(subLevelsEntity.value, subLevel),
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
 * Obtiene el Status del subnivel.
 *
 * @param sublevelsEntity la lista de entidades de subniveles.
 * @param sublevel el modelo de subnivel.
 * @return el estado del juego para el subnivel. Si no se encuentra ninguna entidad de subnivel
 *         con el mismo nombre que el subnivel proporcionado, se devuelve [StatusGame.BLOCKED].
 */
fun getStatusForRoom(sublevelsEntity: List<SubLevelEntity>, sublevel: SubLevelModel): StatusGame {
    return sublevelsEntity.firstOrNull { it.idSubLevel == sublevel.name }?.status
        ?: StatusGame.BLOCKED
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