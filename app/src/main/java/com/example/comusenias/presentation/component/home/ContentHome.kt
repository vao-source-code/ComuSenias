package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.example.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.example.comusenias.presentation.ui.theme.ERROR_RETRY_LEVEL
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.size14
import com.example.comusenias.presentation.view_model.LevelViewModel

lateinit var getLevelViewModel: LevelViewModel

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
                status = subLevel.isCompleted,
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

fun getSubLevel(level: List<LevelModel>): List<SubLevelModel> {
    val listSubLevel = mutableListOf<SubLevelModel>()
    level.forEach {
        listSubLevel += it.subLevel
    }
    return listSubLevel
}