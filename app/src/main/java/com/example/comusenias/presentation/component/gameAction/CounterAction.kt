package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CounterAction() {
    var count by remember { mutableStateOf(6) }

    LaunchedEffect(Unit) {
        while (count > 0) {
            delay(1000) // Retraso de 1 segundo
            count--
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Termina en: ${count.toString()}",
            style = MaterialTheme.typography.h4,
            color = if (count < 3) Color.Red else Color.Green
        )
    }
}

