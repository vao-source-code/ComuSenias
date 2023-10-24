package com.example.comusenias.presentation.component.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.constants.TestTag
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.cardGray
import com.example.comusenias.presentation.ui.theme.cardInProgress
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.iconColorProgress
import com.example.comusenias.presentation.ui.theme.size24
import com.example.comusenias.presentation.ui.theme.size45
import com.example.comusenias.presentation.ui.theme.size5

@Composable
fun ContentCardGame(
    status: StatusGame,
    level: String,
    subLevel: SubLevelModel,
    navController: NavController
) {
    val lineColor: Color
    val backgroundColorCard: Color
    val iconColor: Color
    val blur: Int
    val iconImage: Int

    when (status) {
        StatusGame.COMPLETED -> {
            lineColor = greenColorApp
            backgroundColorCard = Color.White
            iconColor = greenColorApp
            iconImage = R.drawable.check_circle
            blur = 0
        }

        StatusGame.IN_PROGRESS -> {
            lineColor = cardGray
            backgroundColorCard = cardInProgress
            iconColor = iconColorProgress
            blur = 0
            iconImage = R.drawable.time_icon
        }
        StatusGame.BLOCKED -> {
            lineColor = cardGray
            backgroundColorCard = cardInProgress
            iconColor = iconColorProgress
            blur = 0
            iconImage = R.drawable.time_icon
        }

        /*StatusGame.BLOCKED -> {
            lineColor = cardGray
            backgroundColorCard = Color.White
            iconColor = blackColorApp
            iconImage = R.drawable.padlock
            blur = 3
        }*/
    }

    val linecColorAnimate = animateColorAsState(lineColor, label = "")
    val backgroundColorCardAnimate = animateColorAsState(backgroundColorCard, label = "")
    val iconColorAnimate = animateColorAsState(iconColor, label = "")
    val blurAnimate = animateDpAsState(targetValue = blur.dp, label = "")
    val iconAnimate = animateIntAsState(targetValue = iconImage, label = "")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .blur(radius = blurAnimate.value)
            .background(Color.White)
            .testTag(TestTag.TAG_CONTENT_CARD_GAME + status.name)
    ) {
        CardGame(
            lineColor = linecColorAnimate,
            backgroundCard = backgroundColorCardAnimate,
            iconColor = iconColorAnimate,
            icon = iconAnimate,
            level = level,
            subLevel = subLevel,
            navController = navController
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = size45.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(size24.dp)
                    .width(size5.dp)
                    .background(
                        color = linecColorAnimate.value,
                        shape = RoundedCornerShape(SIZE12.dp)
                    )
                    .testTag(TestTag.TAG_LINE_COLOR_CARD)
            )
        }
    }
}

enum class StatusGame {
    COMPLETED,
    IN_PROGRESS,
    BLOCKED
}