package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.size13

@Composable
fun ContentLevel() {
    Column(
        verticalArrangement = Arrangement.spacedBy(SIZE2.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Nivel 1",
            style = TextStyle(
                fontSize = SIZE24.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp
            )
        )
        Text(
            text = "2/5 lecciones completadas",
            style = TextStyle(
                fontSize = size13.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp
            )
        )
    }
}