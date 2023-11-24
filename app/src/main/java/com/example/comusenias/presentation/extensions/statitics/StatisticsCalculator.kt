package com.example.comusenias.presentation.extensions.statitics

import android.util.Log
import androidx.compose.ui.graphics.Color.Companion.Red
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.FAILURE
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.SUCCESS
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.TOTAL
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.line.LineChartData.Point

object StatisticsCalculator {

    private var lastConvertedValue: Int = 0

    enum class AttemptType {
        SUCCESS,
        FAILURE,
        TOTAL,
    }

    fun sumStatistics(
        bars: List<Bar>
    ): Int {
        return bars.sumOf { it.value.toInt() }
    }

    fun createBarList(
        subLevels: List<SubLevelModel>,
        attemptType: AttemptType
    ): List<Bar> {
        val bars = subLevels.map { subLevel ->
            val value = when (attemptType) {
                SUCCESS -> subLevel.successes.toFloat()
                FAILURE -> subLevel.failures.toFloat()
                TOTAL -> subLevel.failures.toFloat() + subLevel.successes.toFloat()
            }
            val color = when (attemptType) {
                SUCCESS -> greenColorApp
                FAILURE -> Red
                TOTAL -> primaryColorApp
            }
            Bar(value = value, color = color, label = subLevel.name)
        }
        return bars
    }

    fun getValuesPointList(
        subLevels: List<SubLevelModel>,
        attemptType: AttemptType
    ): List<Point> {
        val barList = createBarList(subLevels, attemptType)
        return barList.map { Point(it.value, it.label) }
    }

    fun isValidIntegerNumber(value: Float): String {
        Log.v("value", value.toString())
        val rounded = value.toInt()

        if (rounded == lastConvertedValue) {
            lastConvertedValue += 1
        } else {
            lastConvertedValue = rounded
        }

        return lastConvertedValue.toString()
    }
}

