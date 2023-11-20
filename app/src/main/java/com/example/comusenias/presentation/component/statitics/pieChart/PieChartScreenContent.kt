package com.example.comusenias.presentation.component.statitics.pieChart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.statitics.StatisticsDescriptions
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.line_divisor
import com.github.tehras.charts.piechart.PieChartData

@Composable
fun PieChartScreenContent(
    valueSuccess: Float,
    valueFailure: Float,
    valueTotal: Float = 0f
) {
    val responseSuccess = PieChartData.Slice(
        value = valueSuccess,
        color = greenColorApp
    )

    val responseFailure = PieChartData.Slice(
        value = valueFailure,
        color = Red
    )
    var sliceThickness by remember {
        mutableStateOf(25f)
    }

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
        verticalArrangement = Arrangement.spacedBy(SIZE10.dp)
    ) {
        StatisticsDescriptions(
            valueSuccess = valueSuccess,
            valueFailure = valueFailure,
            valueTotal = valueTotal
        )
        PieChartRow(
            pieChartData = pieChart,
            sliceThickness = sliceThickness
        )
        SliceThicknessRow(sliceThickness) {
            sliceThickness = it
        }
        Divider(Modifier.height(SIZE1.dp), color = line_divisor)
    }
}