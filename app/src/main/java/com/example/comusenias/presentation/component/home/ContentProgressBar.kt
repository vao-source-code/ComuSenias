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
import com.example.comusenias.domain.models.room.SubLevelEntity
import com.example.comusenias.presentation.component.home.StatusGame.COMPLETED
import com.example.comusenias.presentation.ui.theme.PROGRESS
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE18
import com.example.comusenias.presentation.ui.theme.SIZE9
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun ContentProgressBar(levels: List<LevelModel>?,sublevelsEntity: List<SubLevelEntity>) {

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
        levels.let {
            ProgressBar(progress = calculateProgress(levels,sublevelsEntity))
        }

    }
}

//private fun calculateProgress(levels: List<LevelModel>?,sublevelsEntity: List<SubLevelEntity>): Float {
//    var progressBar = 0.0f
//
//    levels.let {
//        var totalSubLevels = 0
//        var subLevelscompleted = 0
//
//        levels?.forEach { level ->
//            totalSubLevels += level.subLevel.count()
//        }
//
//        levels?.forEach { level ->
//             level.subLevel.forEach { subLevel ->
//                 if (GameUtils.getStatusSubLevel(sublevelsEntity,subLevel) == StatusGame.COMPLETED) {
//                     subLevelscompleted += 1
//                 }
//            }
//        }
//        progressBar = subLevelscompleted / totalSubLevels.toFloat()
//    }
//
//    return progressBar
//}

private fun calculateProgress(levels: List<LevelModel>?, sublevelsEntity: List<SubLevelEntity>): Float {
    val totalSubLevels = levels?.sumOf { it.subLevel.size } ?: 0
    val subLevelsCompleted = levels?.sumOf { level ->
        level.subLevel.count { subLevel ->
            GameUtils.getStatusSubLevel(sublevelsEntity, subLevel) == COMPLETED
        }
    } ?: 0

    return if (totalSubLevels != 0) subLevelsCompleted.toFloat() / totalSubLevels else 0.0f
}