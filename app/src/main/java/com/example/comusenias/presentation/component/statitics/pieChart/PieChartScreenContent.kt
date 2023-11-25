package com.example.comusenias.presentation.component.statitics.pieChart

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.component.statitics.StatisticsDescriptions
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.FAILURE
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.SUCCESS
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.TOTAL
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.createBarList
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.sumStatistics
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.line_divisor
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.PieChartData.Slice

@Composable
fun PieChartScreenContent(
    subLevel: MutableList<SubLevelModel>
) {
    val statisticsSuccess = createBarList(
        subLevel,
        SUCCESS
    )

    val statisticsFailure = createBarList(
        subLevel,
        FAILURE
    )

    val statisticsTotal = createBarList(
        subLevel,
        TOTAL
    )

    val responseSuccess = Slice(
        value = sumStatistics(statisticsSuccess).toFloat(),
        color = greenColorApp
    )

    val responseFailure = Slice(
        value = sumStatistics(statisticsFailure).toFloat(),
        color = Red
    )

    val pieChart by remember {
        mutableStateOf(
            PieChartData(
                slices = listOf(
                    responseSuccess,
                    responseFailure
                )
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE40.dp),
        verticalArrangement = spacedBy(SIZE10.dp)
    ) {
        StatisticsDescriptions(
            statisticsSuccess = statisticsSuccess,
            statisticsFailures = statisticsFailure,
            statisticsTotal = statisticsTotal
        )
        PieChartRow(
            pieChartData = pieChart,
            sliceThickness = SIZE100.toFloat()
        )
        Divider(Modifier.height(SIZE1.dp), color = line_divisor)
    }
}