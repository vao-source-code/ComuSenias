package com.example.comusenias.presentation.component.gameAction

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE73
import com.example.comusenias.presentation.ui.theme.SIZE84
import com.example.comusenias.presentation.ui.theme.borderButtonLetter
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.size30

@Composable
fun MatchSign(
    singLetter: String,
    randomLetter: String,
    responseMatchLetter: (Boolean) -> Unit
) {
    var isEnable by remember { mutableStateOf(true) }
    val isEnableButtonLetter = remember { mutableStateOf(isEnable) }
    var mutableRandomLetter by remember { mutableStateOf(randomLetter) }

    LaunchedEffect(mutableRandomLetter) {
        isEnableButtonLetter.value = true
    }

    val letters = listOf(singLetter, randomLetter)
    val randomLetters = letters.shuffled()

    Row(
        modifier = Modifier
            .padding(top = size30.dp),
        horizontalArrangement = Arrangement.spacedBy(size30.dp)
    ) {
        randomLetters.forEach { letter ->
            ButtonLetter(
                letter = letter,
                letterCompare = singLetter,
                isEnable = isEnableButtonLetter.value
            ) {
                responseMatchLetter(it)
                isEnableButtonLetter.value = false
            }
        }
    }
}

@Composable
fun ButtonSign(
    image: String,
    letter : String,
    letterCompare: String,
    isEnable: Boolean ,
    matchSign: (Boolean) -> Unit
) {
    var status by remember { mutableStateOf(StatusSign.NORMAL) }

    val borderColor by animateColorAsState(
        when (status) {
            StatusSign.NORMAL -> borderButtonLetter
            StatusSign.CORRECT -> greenColorApp
            StatusSign.ERROR -> Color.Red
        }, label = EMPTY_STRING
    )

    Box(
        modifier = Modifier
            .border(
                width = SIZE2.dp,
                color = borderColor,
                shape = RoundedCornerShape(SIZE12.dp)
            )
            .width(SIZE84.dp)
            .height(SIZE73.dp)
            .clickable {
                if (isEnable) {
                    status = if (letter.equals(letterCompare, ignoreCase = true)) {
                        matchSign(true)
                        StatusSign.CORRECT
                    } else {
                        matchSign(false)
                        StatusSign.ERROR
                    }
                }
            }
    ) {

    }
}