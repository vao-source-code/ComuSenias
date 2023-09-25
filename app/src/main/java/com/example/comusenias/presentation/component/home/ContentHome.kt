package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.size1
import com.example.comusenias.presentation.ui.theme.size14

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentHome() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = size14.dp,
                vertical = size14.dp
            ),
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