package com.example.comusenias.presentation.component.statitics.barChart

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
import com.example.comusenias.presentation.component.statitics.ButtonsStatistics
import com.example.comusenias.presentation.component.statitics.StatisticsDescriptions
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.line_divisor
import com.github.tehras.charts.bar.BarChartData.Bar

@Composable
fun ContentBarChart(
    statisticsSuccess:  List<Bar>,
    statisticsFailures:  List<Bar>,
    valueSuccess: Float = 0f,
    valueFailure: Float = 0f,
    valueTotal: Float = 0f
) {
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
            valueSuccess = valueSuccess,
            valueFailure = valueFailure,
            valueTotal = valueTotal
        )
        MyBarChartParent(statistic = statistic)
        ButtonsStatistics(
            onClickCorrect = {
                statistic = statisticsSuccess
            },
            onClickIncorrect = {
                statistic = statisticsFailures
            }
        )
        Divider(Modifier.height(SIZE1.dp), color = line_divisor)
    }
}