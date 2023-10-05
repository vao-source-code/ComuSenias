package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.size08
import com.example.comusenias.presentation.ui.theme.size30

@Composable
fun GameAction(
    imageSign: Int,
    title: String,
    titleButton: String,
    clickButton: () -> Unit,
    enabledButton: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(size30.dp)
    ) {
        TopSectionGameAction(
            title = title,
            image = imageSign
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(size08),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
        BottomSectionGameAction(
            titleButton = titleButton,
            enabled = enabledButton,
            clickButton = { clickButton() }
        )
    }
}


