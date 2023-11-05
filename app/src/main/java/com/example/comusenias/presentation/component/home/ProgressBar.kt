package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.SIZE15

@Composable
fun ProgressBar(progress: Float ) {

    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE15.dp)
            .clip(RoundedCornerShape(SIZE16.dp)),
        progress = progress,
        backgroundColor = Color.LightGray,
        color = greenColorApp
    )
}