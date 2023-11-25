package com.example.comusenias.presentation.component.statitics.lineChart

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
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.component.statitics.ButtonsStatistics
import com.example.comusenias.presentation.component.statitics.StatisticsDescriptions
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.FAILURE
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.SUCCESS
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.getValuesPointList
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.line_divisor
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer

@Composable
fun ContentLineChart(
    subLevel: MutableList<SubLevelModel>
) {
    val correctList = getValuesPointList(subLevel, SUCCESS)
    val incorrectList = getValuesPointList(subLevel, FAILURE)

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
        StatisticsCalculator.AttemptType.TOTAL
    )

    val lineChartCorrect = LineChartData(
        points = correctList,
        lineDrawer = SolidLineDrawer(
            color = greenColorApp
        )
    )

    val lineChartIncorrect = LineChartData(
        points = incorrectList,
        lineDrawer = SolidLineDrawer(
            color = Red
        )
    )

    var statistic by remember {
        mutableStateOf(lineChartCorrect)
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
        MyLineChartParent(statistic = statistic)
        ButtonsStatistics(
            onClickCorrect = {
                statistic = lineChartCorrect

            },
            onClickIncorrect = {
                statistic = lineChartIncorrect
            }
        )
        Divider(Modifier.height(SIZE1.dp), color = line_divisor)
    }
}