package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.SIZE30

@Composable
fun ContentLetterType(letter: String) {
    Text(
        modifier = Modifier
            .padding(SIZE30.dp),
        text = letter.uppercase(),
        style = TextStyle(
            fontSize = SIZE36.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp,
        )
    )
}