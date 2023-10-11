package com.example.comusenias.presentation.component.gameAction

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.screen.gameAction.Sign
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE120
import com.example.comusenias.presentation.ui.theme.SIZE150
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.example.comusenias.presentation.ui.theme.borderButtonLetter
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.SIZE30

@Composable
fun MatchSign(
    sign: Sign,
    randomSign: Sign,
    letterCompare: String,
    responseMatchLetter: (Boolean) -> Unit
) {
    var isEnable by remember { mutableStateOf(true) }
    val isEnableButtonLetter = remember { mutableStateOf(isEnable) }

    val signs = listOf(sign, randomSign)
    val randomLetters = signs.shuffled()

    Row(
        modifier = Modifier
            .padding(top = SIZE30.dp),
        horizontalArrangement = Arrangement.spacedBy(SIZE30.dp)
    ) {
        randomLetters.forEach { sign ->
            ButtonSign(
                sign = sign,
                letterCompare = letterCompare,
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
    sign: Sign,
    letterCompare: String,
    isEnable: Boolean ,
    matchSign: (Boolean) -> Unit
) {
    val statusLetter by remember { mutableStateOf(sign.letter) }
    val statusLetterCompare by remember { mutableStateOf(letterCompare) }
    val statusImage by remember { mutableStateOf(sign.imageResource) }
    var status by remember { mutableStateOf(StatusSign.NORMAL) }

    val borderColor by animateColorAsState(
        when (status) {
            StatusSign.NORMAL -> borderButtonLetter
            StatusSign.CORRECT -> greenColorApp
            StatusSign.ERROR -> Color.Red
        }, label = EMPTY_STRING
    )
    val borderWidth by animateDpAsState(
        when (status) {
            StatusSign.NORMAL -> SIZE2.dp
            StatusSign.CORRECT, StatusSign.ERROR -> SIZE5.dp
        }, label = EMPTY_STRING
    )

    Box(
        modifier = Modifier
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(SIZE12.dp)
            )
            .width(SIZE120.dp)
            .height(SIZE150.dp)
            .clickable {
                if (isEnable) {
                    status = if (statusLetter.equals(statusLetterCompare, ignoreCase = true)) {
                        matchSign(true)
                        StatusSign.CORRECT
                    } else {
                        matchSign(false)
                        StatusSign.ERROR
                    }
                }
            }
            .testTag(TestTag.TAG_MATCH_SIGN + sign.letter)
    ) {
        Image(
            painter = painterResource(statusImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}