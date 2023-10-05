package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE4
import com.example.comusenias.presentation.ui.theme.SIZE70
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.size150

@Composable
fun SignInterpretationStatus() {
    Box(
        modifier = Modifier
            .size(size150.dp)
            .padding(top = SIZE70.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize(),
            color = greenColorApp,
            strokeWidth = SIZE4.dp
        )
    }
}