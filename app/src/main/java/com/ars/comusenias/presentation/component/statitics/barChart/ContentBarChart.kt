package com.ars.comusenias.presentation.component.statitics.barChart

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.presentation.component.statitics.ButtonsStatistics
import com.ars.comusenias.presentation.component.statitics.StatisticsDescriptions
import com.ars.comusenias.presentation.extensions.statitics.StatisticsCalculator
import com.ars.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.FAILURE
import com.ars.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.TOTAL
import com.ars.comusenias.presentation.ui.theme.SIZE1
import com.ars.comusenias.presentation.ui.theme.SIZE30
import com.ars.comusenias.presentation.ui.theme.SIZE40
import com.ars.comusenias.presentation.ui.theme.line_divisor

@Composable
fun ContentBarChart(
    subLevel: MutableList<SubLevelModel>
) {
    val statisticsSuccess = StatisticsCalculator.createBarList(
        subLevel,
        StatisticsCalculator.AttemptType.SUCCESS
    )

    val statisticsFailure = StatisticsCalculator.createBarList(
        subLevel,
        FAILURE
    )

    val statisticsTotal = StatisticsCalculator.createBarList(
        subLevel,
        TOTAL
    )

    var statistic by remember {
        mutableStateOf(statisticsSuccess)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE40.dp),
        verticalArrangement = spacedBy(SIZE30.dp)
    ) {
        StatisticsDescriptions(
            statisticsSuccess = statisticsSuccess,
            statisticsFailures = statisticsFailure,
            statisticsTotal = statisticsTotal
        )
        MyBarChartParent(statistic = statistic)
        ButtonsStatistics(
            onClickCorrect = {
                statistic = statisticsSuccess
            },
            onClickIncorrect = {
                statistic = statisticsFailure
            }
        )
        Divider(Modifier.height(SIZE1.dp), color = line_divisor)
    }
}