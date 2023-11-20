package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.CORRECT
import com.example.comusenias.presentation.ui.theme.INCORRECT
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.greenColorApp

@Composable
fun ButtonsStatistics(
    onClickCorrect: () -> Unit,
    onClickIncorrect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SIZE16.dp),
        horizontalArrangement = Center
    ) {
        ButtonStatistic(
            titleButton = CORRECT,
            color = greenColorApp,
            onClickButton = {
                onClickCorrect()
            }
        )

        Spacer(modifier = Modifier.width(SIZE20.dp))

        ButtonStatistic(
            titleButton = INCORRECT,
            color = Red,
            onClickButton = {
                onClickIncorrect()
            }
        )
    }
}