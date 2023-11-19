package com.example.comusenias.presentation.extensions.statitics

import androidx.compose.ui.graphics.Color
import com.example.comusenias.domain.models.statistics.StatisticsModel
import com.example.comusenias.presentation.screen.specialist.SubLevelModelMock
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.line.LineChartData.Point

object StatisticsCalculator {

    enum class AttemptType {
        SUCCESS,
        FAILURE
    }

    fun createBarList(
        subLevels: List<SubLevelModelMock>,
        color: Color,
        description: String,
        attemptType: AttemptType
    ): StatisticsModel {
        val bars = subLevels.map { subLevel ->
            val value = when (attemptType) {
                AttemptType.SUCCESS -> subLevel.successes.toFloat()
                AttemptType.FAILURE -> subLevel.failures.toFloat()
            }
            Bar(value = value, color = color, label = subLevel.name)
        }
        return StatisticsModel(description = description, barChartData = bars)
    }

//    fun calculateDifference(vowelResultsCompleted: StatisticsModel, vowelResultsFail: StatisticsModel): List<Point> {
//        val result = mutableListOf<Point>()
//
//        for (i in vowelResultsCompleted.indices) {
//            val completedValue = vowelResultsCompleted[i].value
//            val failValue = vowelResultsFail[i].value
//            val difference = completedValue - failValue
//
//            result.add(Point(difference, vowelResultsCompleted[i].label))
//        }
//
//        return result
//    }
//
//    fun getValuesPointList(barList: StatisticsModel): List<Point> {
//        return barList.map { Point(it.value, it.label) }
//    }
}