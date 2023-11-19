package com.example.comusenias.presentation.component.specialist.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.PATIENTS
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun Title(title: String = PATIENTS, textAlign: TextAlign = Start) {

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        style = TextStyle(
            fontSize = SIZE24.sp,
            fontWeight = Bold,
            color = blackColorApp,
            textAlign = textAlign
        )
    )
}