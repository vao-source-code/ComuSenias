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
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.ui.theme.size1
import com.example.comusenias.presentation.ui.theme.size14

@Composable
fun ContentHome(navController: NavHostController, modifier: Modifier) {
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
            ContentLevel()
        }
        items(statusCards()) { statusGame ->
            ContentCardGame(status = statusGame)
        }
        item {
            CardGameCheckPoint()
        }
    }
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