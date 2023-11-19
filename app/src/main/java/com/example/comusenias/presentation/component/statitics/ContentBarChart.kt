package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.domain.models.statistics.StatisticsModel
import com.example.comusenias.presentation.component.specialist.home.Title
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE40

@Composable
fun ContentBarChart(statistics: List<StatisticsModel>) {
    var statisticIndex by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE40.dp),
        verticalArrangement = spacedBy(SIZE30.dp)
    ) {
        Title(title = statistics[statisticIndex].description)
        MyBarChartParent(statistic = statistics[statisticIndex])
        ButtonsStatistics(
            onClickCorrect = {
                statisticIndex = 0
            },
            onClickIncorrect = {
                statisticIndex = 1
            }
        )
    }
}