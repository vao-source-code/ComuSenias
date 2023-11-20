package com.example.comusenias.presentation.extensions.statitics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.FAILURE
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.SUCCESS
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.TOTAL
import com.example.comusenias.presentation.screen.specialist.SubLevelModelMock
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.line.LineChartData.Point

object StatisticsCalculator {

    enum class AttemptType {
        SUCCESS,
        FAILURE,
        TOTAL,
    }

    fun sumStatistics(
        subLevel: List<SubLevelModelMock>,
        attemptType: AttemptType
    ): Int {
        val value = when (attemptType) {
            SUCCESS -> subLevel.sumOf { it.successes }
            FAILURE -> subLevel.sumOf { it.failures }
            TOTAL -> subLevel.sumOf { it.successes } + subLevel.sumOf { it.failures }
        }
        return value
    }

    fun createBarList(
        subLevels: List<SubLevelModelMock>,
        attemptType: AttemptType
    ): List<Bar> {
        val bars = subLevels.map { subLevel ->
            val value = when (attemptType) {
                SUCCESS -> subLevel.successes.toFloat()
                FAILURE -> subLevel.failures.toFloat()
                else -> {
                    0f
                }
            }
            val color = when (attemptType) {
                SUCCESS -> greenColorApp
                FAILURE -> Red
                else -> {
                    Color.Black
                }
            }
            Bar(value = value, color = color, label = subLevel.name)
        }
        return bars
    }

    fun getValuesPointList(barList: List<Bar>): List<Point> {
        return barList.map { Point(it.value, it.label) }
    }
}