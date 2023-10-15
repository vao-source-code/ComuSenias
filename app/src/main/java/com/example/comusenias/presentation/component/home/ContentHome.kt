package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.models.users.KidsMock
import com.example.comusenias.presentation.component.circularProgressIndicator.ShowProgressIndicator
import com.example.comusenias.presentation.ui.theme.size1
import com.example.comusenias.presentation.ui.theme.size14
import com.example.comusenias.presentation.view_model.LevelViewModel

@Composable
fun ContentHome(
    modifier: Modifier,
    navController: NavController,
    levelViewModel: LevelViewModel = hiltViewModel(),
) {

    //  val subLevel = KidsMock.instance.subLevel
//    val level = KidsMock.instance.levelList.first()
    val level = levelViewModel.levels

    when (levelViewModel.levelsResponse) {
        is Response.Loading -> {
            ShowProgressIndicator()
        }

        is Response.Error -> {
            Snackbar {
                Text(text = "Error")
            }
        }

        is Response.Success -> {
            showLazyColumn(level, navController)
        }

        else -> {
            ContentProgressBar()
        }
    }
}

@Composable
private fun showLazyColumn(
    level: List<LevelModel>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = size14.dp,
                end = size14.dp,
            )
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(size1.dp)
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
    level.forEach { level ->
        listSubLevel += level.subLevel
    }
    return listSubLevel
}

fun statusCards(): List<StatusGame> {
    return listOf(
        StatusGame.COMPLETED,
        StatusGame.COMPLETED,
        StatusGame.IN_PROGRESS,
        StatusGame.BLOCKED,
        StatusGame.BLOCKED
    )
}