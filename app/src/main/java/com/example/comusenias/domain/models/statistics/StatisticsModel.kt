package com.example.comusenias.domain.models.statistics

import com.github.tehras.charts.bar.BarChartData.Bar

data class StatisticsModel(
    val description: String,
    val barChartData: List<Bar>
)
