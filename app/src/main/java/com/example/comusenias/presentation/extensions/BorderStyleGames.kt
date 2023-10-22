package com.example.comusenias.presentation.extensions

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.example.comusenias.presentation.ui.theme.borderButtonLetter
import com.example.comusenias.presentation.ui.theme.secondaryColorApp

class BorderStyleGames(
    private var selectedButtonIndex: MutableState<Int>,
    var index: Int
) {
    @Composable
    fun getBorderColor(): Color {
        val borderColor by animateColorAsState(
            if (selectedButtonIndex.value == index) {
                secondaryColorApp
            } else {
                borderButtonLetter
            }, label = EMPTY_STRING
        )
        return borderColor
    }

    @Composable
    fun getBorderWidth(): Dp {
        val borderWidth by animateDpAsState(
            if (selectedButtonIndex.value == index) {
                SIZE5.dp
            } else {
                SIZE2.dp
            }, label = EMPTY_STRING
        )
        return borderWidth
    }
}