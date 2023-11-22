package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.comusenias.R
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.extensions.validation.getChoicesSelected
import com.example.comusenias.presentation.ui.theme.CONGRATS_TEXT
import com.example.comusenias.presentation.ui.theme.ERROR_CONGRATS_TEXT
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE300
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun CongratsContent() {
    val congratsImage by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_congrats))
    val congratsErrorImage by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.dino_error))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE100.dp)
    ) {
        LottieAnimation(
            modifier = Modifier.height(SIZE300.dp),
            composition = showImage(congratsImage, congratsErrorImage),
            iterations = LottieConstants.IterateForever,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = showText(),
            style = TextStyle(
                fontSize = SIZE24.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
private fun showText() =
    if (getChoicesSelected(getLevelViewModel)) CONGRATS_TEXT else ERROR_CONGRATS_TEXT

@Composable
private fun showImage(
    congratsImage: LottieComposition?,
    congratsErrorImage: LottieComposition?
) = if (getChoicesSelected(getLevelViewModel)) congratsImage else congratsErrorImage