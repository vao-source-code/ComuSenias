package com.ars.comusenias.presentation.component.statitics.lineChart

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import com.ars.comusenias.presentation.extensions.statitics.CustomYaxisDrawer
import com.ars.comusenias.presentation.extensions.statitics.StatisticsCalculator
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE160
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Composable
fun MyLineChartParent(statistic: LineChartData) {
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE160.dp),
        linesChartData = listOf(statistic),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(
            diameter = SIZE10.dp,
            color = Black
        ),
        yAxisDrawer = CustomYaxisDrawer(
            labelValueFormatter = { value ->
                (StatisticsCalculator.isValidIntegerNumber(value))
            }
        )
    )
}