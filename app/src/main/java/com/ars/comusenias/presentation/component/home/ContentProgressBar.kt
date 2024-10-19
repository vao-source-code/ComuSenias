package com.ars.comusenias.presentation.component.home

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.presentation.component.home.StatusGame.COMPLETED
import com.ars.comusenias.presentation.ui.theme.PROGRESS
import com.ars.comusenias.presentation.ui.theme.SIZE0
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE18
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.ui.theme.SIZE24
import com.ars.comusenias.presentation.ui.theme.SIZE9
import com.ars.comusenias.presentation.ui.theme.WELCOME
import com.ars.comusenias.presentation.ui.theme.blackColorApp
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel

@Composable
fun TopHomeContent(levels: List<LevelModel>?, sublevelsEntity: List<SubLevelModel>, viewModel: ChildrenProfileViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE10.dp),
        verticalArrangement = spacedBy(SIZE2.dp, CenterVertically),
        horizontalAlignment = Start,
    ) {
        CircleWithProfileImage(viewModel = viewModel)
        Text(
            text = WELCOME,
            style = TextStyle(
                fontSize = SIZE14.sp,
                fontWeight = SemiBold,
                color = Color.White
            )
        )

        Text(
            text = viewModel.userData.name,
            style = TextStyle(
                fontSize = SIZE24.sp,
                fontWeight = SemiBold,
                color = Color.White
            )
        )

        Text(
            text = PROGRESS,
            style = TextStyle(
                fontSize = SIZE18.sp,
                fontWeight = SemiBold,
                color = Color.White
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
            getStatusSubLevel(subLevel) == COMPLETED
        }
    } ?: SIZE0
    return if (totalSubLevels != 0) subLevelsCompleted.toFloat() / totalSubLevels else 0.0f
}
