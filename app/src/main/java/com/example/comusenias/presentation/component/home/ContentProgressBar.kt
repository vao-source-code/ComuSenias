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
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.component.home.StatusGame.COMPLETED
import com.example.comusenias.presentation.ui.theme.PROGRESS
import com.example.comusenias.presentation.ui.theme.SIZE0
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE18
import com.example.comusenias.presentation.ui.theme.SIZE9
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun ContentProgressBar(levels: List<LevelModel>?, sublevelsEntity: List<SubLevelModel>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE10.dp)
            .background(White),
        verticalArrangement = spacedBy(SIZE9.dp, CenterVertically),
        horizontalAlignment = Start,
    ) {
        Text(
            text = PROGRESS,
            style = TextStyle(
                fontSize = SIZE18.sp,
                fontWeight = SemiBold,
                color = blackColorApp
            )
        )
        levels?.let {
            ProgressBar(progress = calculateProgress(levels))
        }
    }
}

private fun calculateProgress(levels: List<LevelModel>?): Float {
    val totalSubLevels = levels?.sumOf { it.subLevel.size } ?: SIZE0
    val subLevelsCompleted = levels?.sumOf { level ->
        level.subLevel.count { subLevel ->
            GameUtils.getStatusSubLevel(subLevel) == COMPLETED
        }
    } ?: SIZE0
    return if (totalSubLevels != 0) subLevelsCompleted.toFloat() / totalSubLevels else 0.0f
}