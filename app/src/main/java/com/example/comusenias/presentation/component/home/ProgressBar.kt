package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.size15

@Composable
fun ProgressBar() {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(size15.dp),
        progress = 0.7f,
        backgroundColor = Color.LightGray,
        color = greenColorApp
    )
}