package com.example.comusenias.presentation.component.gameAction

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE73
import com.example.comusenias.presentation.ui.theme.SIZE84
import com.example.comusenias.presentation.ui.theme.borderButtonLetter
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.size30
import com.example.comusenias.presentation.ui.theme.size50

enum class StatusSign {
    NORMAL,
    CORRECT,
    ERROR
}

@Composable
fun MatchLetter(
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
fun ButtonLetter(
    letter: String,
    letterCompare: String,
    isEnable: Boolean ,
    matchLetter: (Boolean) -> Unit
) {
    val statusLetter by remember { mutableStateOf(letter) }
    var status by remember { mutableStateOf(StatusSign.NORMAL) }

    val borderColor by animateColorAsState(
        when (status) {
            StatusSign.NORMAL -> borderButtonLetter
            StatusSign.CORRECT -> greenColorApp
            StatusSign.ERROR -> Color.Red
        }, label = EMPTY_STRING
    )
    val backgroundColor by animateColorAsState(
        when (status) {
            StatusSign.NORMAL -> Color.White
            StatusSign.CORRECT -> greenColorApp
            StatusSign.ERROR -> Color.Red
        }, label = EMPTY_STRING
    )
    val letterColor by animateColorAsState(
        if (status == StatusSign.NORMAL) Color.Black else Color.White, label = EMPTY_STRING
    )

    Box(
        modifier = Modifier
            .border(
                width = SIZE2.dp,
                color = borderColor,
                shape = RoundedCornerShape(SIZE12.dp)
            )
            .background(
                backgroundColor,
                shape = RoundedCornerShape(SIZE12.dp)
            )
            .width(SIZE84.dp)
            .height(SIZE73.dp)
            .clickable {
                if (isEnable) {
                    status = if (letter.equals(letterCompare, ignoreCase = true)) {
                        matchLetter(true)
                        StatusSign.CORRECT
                    } else {
                        matchLetter(false)
                        StatusSign.ERROR
                    }
                }
            }
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = statusLetter.uppercase(),
            style = TextStyle(
                fontSize = size50.sp,
                fontWeight = FontWeight.SemiBold,
                color = letterColor,
                textAlign = TextAlign.Center
            )
        )
    }
}
