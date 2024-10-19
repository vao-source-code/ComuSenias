package com.ars.comusenias.presentation.component.home

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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ars.comusenias.R.drawable.check_circle
import com.ars.comusenias.R.drawable.padlock
import com.ars.comusenias.R.drawable.time_icon
import com.ars.comusenias.constants.TestTag.Companion.TAG_CONTENT_CARD_GAME
import com.ars.comusenias.constants.TestTag.Companion.TAG_LINE_COLOR_CARD
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.presentation.component.home.StatusGame.BLOCKED
import com.ars.comusenias.presentation.component.home.StatusGame.COMPLETED
import com.ars.comusenias.presentation.component.home.StatusGame.IN_PROGRESS
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.SIZE12
import com.ars.comusenias.presentation.ui.theme.SIZE24
import com.ars.comusenias.presentation.ui.theme.SIZE3
import com.ars.comusenias.presentation.ui.theme.SIZE45
import com.ars.comusenias.presentation.ui.theme.blackColorApp
import com.ars.comusenias.presentation.ui.theme.cardGray
import com.ars.comusenias.presentation.ui.theme.cardInProgress
import com.ars.comusenias.presentation.ui.theme.greenColorApp
import com.ars.comusenias.presentation.ui.theme.iconColorProgress

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
        COMPLETED -> {
            lineColor = greenColorApp
            backgroundColorCard = White
            iconColor = greenColorApp
            iconImage = check_circle
            blur = 0
        }

        IN_PROGRESS -> {
            lineColor = cardGray
            backgroundColorCard = cardInProgress
            iconColor = iconColorProgress
            blur = 0
            iconImage = time_icon
        }

        BLOCKED -> {
            lineColor = cardGray
            backgroundColorCard = White
            iconColor = blackColorApp
            iconImage = padlock
            blur = 3
        }
    }

    val linecColorAnimate = animateColorAsState(lineColor, label = EMPTY_STRING)
    val backgroundColorCardAnimate = animateColorAsState(backgroundColorCard, label = EMPTY_STRING)
    val iconColorAnimate = animateColorAsState(iconColor, label = EMPTY_STRING)
    val blurAnimate = animateDpAsState(targetValue = blur.dp, label = EMPTY_STRING)
    val iconAnimate = animateIntAsState(targetValue = iconImage, label = EMPTY_STRING)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .blur(radius = blurAnimate.value)
            .testTag(TAG_CONTENT_CARD_GAME + status.name)
    ) {
        CardGame(
            lineColor = linecColorAnimate,
            backgroundCard = backgroundColorCardAnimate,
            iconColor = iconColorAnimate,
            icon = iconAnimate,
            level = level,
            subLevel = subLevel,
            navController = navController,
            status = status
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = SIZE45.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(SIZE24.dp)
                    .width(SIZE3.dp)
                    .background(
                        color = linecColorAnimate.value,
                        shape = RoundedCornerShape(SIZE12.dp)
                    )
                    .testTag(TAG_LINE_COLOR_CARD)
            )
        }
    }
}

enum class StatusGame {
    COMPLETED,
    IN_PROGRESS,
    BLOCKED
}