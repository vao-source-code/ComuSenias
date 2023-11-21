package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.specialist.home.Title
import com.example.comusenias.presentation.component.statitics.barChart.ContentBarChart
import com.example.comusenias.presentation.component.statitics.lineChart.ContentLineChart
import com.example.comusenias.presentation.component.statitics.pieChart.PieChartScreenContent
import com.example.comusenias.presentation.screen.specialist.LevelModelMock
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE20


@Composable
fun StatisticsComponent(levelModel: LevelModelMock) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE10.dp)
    ) {
        Title(
            title = levelModel.name,
            textAlign = TextAlign.Center
        )
        ContentBarChart(
            subLevel = levelModel.subLevel
        )
        ContentLineChart(
            subLevel = levelModel.subLevel
        )
        PieChartScreenContent(
            subLevel = levelModel.subLevel
        )
        Spacer(modifier = Modifier.height(SIZE20.dp))
    }
}