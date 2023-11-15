package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.presentation.component.home.StatusGame.COMPLETED
import com.example.comusenias.presentation.ui.theme.COMPLETED_LESSONS
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE13
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun ContentLevel(level: LevelModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE20.dp, bottom = SIZE12.dp)
            .background(White),
        verticalArrangement = spacedBy(SIZE2.dp, CenterVertically),
        horizontalAlignment = Start,
    ) {
        Text(
            text = level.name,
            style = TextStyle(
                fontSize = SIZE24.sp,
                fontWeight = SemiBold,
                color = blackColorApp
            )
        )
        Text(
            text = "${
                countCompletedSubLevels(
                    level,
                )
            }/${totalSublevels(level)} $COMPLETED_LESSONS",
            style = TextStyle(
                fontSize = SIZE13.sp,
                fontWeight = SemiBold,
                color = blackColorApp
            )
        )
    }
}

fun countCompletedSubLevels(level: LevelModel): Int {
    var subLevelCompleted = 0
    level.subLevel.forEach { subLevel ->
        if (getStatusSubLevel(subLevel) == COMPLETED) {
            subLevelCompleted += 1
        }
    }
    return subLevelCompleted
}

fun totalSublevels(level: LevelModel): Int {
    return level.subLevel.count()
}