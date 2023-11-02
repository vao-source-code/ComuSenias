package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE13
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun ContentLevel(levelDescription: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE20.dp, bottom = SIZE12.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(SIZE2.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = levelDescription,
            style = TextStyle(
                fontSize = SIZE24.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp
            )
        )
        Text(
            text = "2/5 lecciones completadas",
            style = TextStyle(
                fontSize = SIZE13.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp
            )
        )
    }
}