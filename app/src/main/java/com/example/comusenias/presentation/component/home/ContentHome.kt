package com.example.comusenias.presentation.component.home

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
import com.example.comusenias.R
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.activities.MainActivity.Companion.getChildrenProfileViewModel
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE14
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.example.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.example.comusenias.presentation.view_model.LevelViewModel

/*val subLevelModelList = mutableListOf<SubLevelModel>(
    SubLevelModel(
        "1",
        "prueba",
        "https://noticias.nmas.com.mx/wp-content/uploads/2022/03/WhatsApp-ok-mano.jpg",
        "https://youtube.com/shorts/8WpMj_E3i3I?feature=share",
        "Permiso",
        "https://youtube.com/shorts/8WpMj_E3i3I?feature=share",
        "https://youtube.com/shorts/8WpMj_E3i3I?feature=share",
        StatusGame.IN_PROGRESS,
        true
    )
)*/
val subLevelModelList = mutableListOf<SubLevelModel>(
    SubLevelModel(
        "1",
        "prueba",
        "https://noticias.nmas.com.mx/wp-content/uploads/2022/03/WhatsApp-ok-mano.jpg",
       "https://firebasestorage.googleapis.com/v0/b/pruebacomu-c4bd2.appspot.com/o/Permiso.mp4?alt=media&token=25801565-2121-4524-819d-b4555e301866",
        "Permiso",
        "https://youtube.com/shorts/8WpMj_E3i3I?feature=share",
        "https://firebasestorage.googleapis.com/v0/b/pruebacomu-c4bd2.appspot.com/o/Permiso.mp4?alt=media&token=25801565-2121-4524-819d-b4555e301866",
        StatusGame.IN_PROGRESS,
        true
    )
)
val listLevelViewModel = mutableListOf<LevelModel>(
    LevelModel(
        "1", "prueba", listOf("1", "2"), subLevelModelList, StatusGame.IN_PROGRESS
    )
)

@Composable
fun ContentHome(
    navController: NavController,
    levelViewModel: LevelViewModel,
    childrenModel: ChildrenProfileViewModel
) {

    ShowLazyColumn(listLevelViewModel, navController)


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
    return StatusGame.IN_PROGRESS
    /* return when {
         lastCompletedIndex != -1 && currentIndex == lastCompletedIndex + 1 -> StatusGame.IN_PROGRESS
         lastCompletedIndex != -1 && currentIndex > lastCompletedIndex + 1 -> StatusGame.BLOCKED
         else -> getAllSubLevels().getOrNull(currentIndex)?.isCompleted ?: StatusGame.BLOCKED
     }*/
}

fun getAllLevels(): List<LevelModel> {
    return getChildrenProfileViewModel.userData.levels
}

fun getAllSubLevels(): List<SubLevelModel> {
    return getChildrenProfileViewModel.userData.levels.flatMap { it.subLevel }
}

/*@Composable
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
}*/
