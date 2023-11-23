package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.statitics.ButtonState.CORRECT_BUTTON
import com.example.comusenias.presentation.component.statitics.ButtonState.INCORRECT_BUTTON
import com.example.comusenias.presentation.ui.theme.CORRECT
import com.example.comusenias.presentation.ui.theme.INCORRECT
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.greenColorApp

enum class ButtonState { CORRECT_BUTTON, INCORRECT_BUTTON }

@Composable
fun ButtonsStatistics(
    onClickCorrect: () -> Unit,
    onClickIncorrect: () -> Unit
) {
    var activeButton by remember { mutableStateOf(CORRECT_BUTTON) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SIZE16.dp),
        horizontalArrangement = Center
    ) {
        ButtonStatistic(
            titleButton = CORRECT,
            color = greenColorApp,
            isButtonPressed = activeButton == CORRECT_BUTTON,
            onClickButton = {
                activeButton = CORRECT_BUTTON
                onClickCorrect()
            }
        )

        Spacer(modifier = Modifier.width(SIZE20.dp))

        ButtonStatistic(
            titleButton = INCORRECT,
            color = Red,
            isButtonPressed = activeButton == INCORRECT_BUTTON,
            onClickButton = {
                activeButton = INCORRECT_BUTTON
                onClickIncorrect()
            }
        )
    }
}