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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.PROGRESS
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE18
import com.example.comusenias.presentation.ui.theme.SIZE9
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Preview(showBackground = true)
@Composable
fun ContentProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE10.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(SIZE9.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = PROGRESS,
            style = TextStyle(
                fontSize = SIZE18.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp
            )
        )
        ProgressBar()
    }
}