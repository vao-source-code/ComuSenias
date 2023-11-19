package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import com.example.comusenias.domain.models.statistics.StatisticsModel
import com.example.comusenias.presentation.extensions.statitics.IntegerYAxisDrawer
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE300
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

//@Composable
//fun MyLineChartParent(statistics: List<StatisticsModel>) {
//    val differenceList = StatisticsCalculator.calculateDifference(statistics[0], statistics[1])
//    val correctList = StatisticsCalculator.getValuesPointList(statistics[0])
//    val incorrectList = StatisticsCalculator.getValuesPointList(statistics[1])
//
//
//    val lineChartDataDifference = LineChartData(
//        points = differenceList,
//        lineDrawer = SolidLineDrawer(
//            color = Black
//        )
//    )
//
//    val lineChartDataCorrect = LineChartData(
//        points = correctList,
//        lineDrawer = SolidLineDrawer(
//            color = greenColorApp
//        )
//    )
//
//    val lineChartDataIncorrect = LineChartData(
//        points = incorrectList,
//        lineDrawer = SolidLineDrawer(
//            color = Color.Red
//        )
//    )
//
//    LineChart(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(SIZE300.dp)
//            .padding(top = SIZE40.dp),
//        linesChartData = listOf(lineChartDataCorrect),
//        animation = simpleChartAnimation(),
//        pointDrawer = FilledCircularPointDrawer(
//            diameter = SIZE20.dp,
//            color = primaryColorApp
//        ),
//        yAxisDrawer = IntegerYAxisDrawer(
//            labelValueFormatter = { value -> "${value.toInt()}" }
//        ),
//    )
//}

