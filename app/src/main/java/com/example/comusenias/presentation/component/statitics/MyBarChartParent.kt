package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import com.example.comusenias.domain.models.statistics.StatisticsModel
import com.example.comusenias.presentation.ui.theme.SIZE300
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer.DrawLocation.XAxis
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Composable
fun MyBarChartParent(statistic: StatisticsModel) {
    BarChart(
        barChartData = BarChartData(
            bars = statistic.barChartData
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE300.dp),
        animation = simpleChartAnimation(),
        barDrawer = SimpleBarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextSize = TextUnit(value = 10f, type = Sp),
            labelValueFormatter = { value -> "${value.toInt()}" },
        ),
        labelDrawer = SimpleValueDrawer(
            drawLocation = XAxis,
            labelTextSize = TextUnit(value = 10f, type = Sp),
            labelTextColor = blackColorApp
        )
    )
}