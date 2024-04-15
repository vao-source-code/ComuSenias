package com.ars.comusenias.presentation.component.specialist.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.presentation.ui.theme.DONT_YOU_HAVE_PATIENTS
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE220
import com.ars.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun DontYouHavePatients() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE220.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = DONT_YOU_HAVE_PATIENTS, style = TextStyle(
                fontSize = SIZE16.sp, fontWeight = FontWeight.Normal, color = blackColorApp
            )
        )
    }
}