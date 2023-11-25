package com.example.comusenias.presentation.component.statitics.pieChart

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE150
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

@Composable
fun PieChartRow(
    pieChartData: PieChartData,
    sliceThickness: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE150.dp)
            .padding()
    ) {
        PieChart(
            pieChartData = pieChartData,
            sliceDrawer = SimpleSliceDrawer(
                sliceThickness = sliceThickness
            )
        )
    }
}
