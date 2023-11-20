package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.CHOICES
import com.example.comusenias.presentation.ui.theme.CORRECT
import com.example.comusenias.presentation.ui.theme.INCORRECT
import com.example.comusenias.presentation.ui.theme.SIZE14
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun StatisticsDescriptions(
    valueSuccess: Float = 0f,
    valueFailure: Float = 0f,
    valueTotal: Float = 0f
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SIZE5.dp)
    ) {
        ContentStatisticDescription(
            color = primaryColorApp,
            value = valueTotal.toInt() ,
            description = CHOICES
        )
        ContentStatisticDescription(
            color = greenColorApp,
            value = valueSuccess.toInt() ,
            description = CORRECT
        )
        ContentStatisticDescription(
            color = Red,
            value = valueFailure.toInt() ,
            description = INCORRECT
        )
    }
}

@Composable
fun ContentStatisticDescription(
    color: Color,
    value: Int,
    description: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SIZE5.dp)
    ) {
       Box(
           modifier = Modifier
               .size(SIZE14.dp)
               .background(color)
       )
        Text(
            text = "${value} ${description}",
            style = TextStyle(
                fontSize = SIZE14.sp,
                fontWeight = SemiBold,
                color = blackColorApp,
            )
        )
    }
}
