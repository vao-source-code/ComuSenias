package com.example.comusenias.presentation.component.specialist.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.PATIENTS
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun Title() {
    Text(
        modifier = Modifier.fillMaxSize(), text = PATIENTS, style = TextStyle(
            fontSize = SIZE24.sp,
            fontWeight = FontWeight.Bold,
            color = blackColorApp,
            textAlign = TextAlign.Start
        )
    )
}