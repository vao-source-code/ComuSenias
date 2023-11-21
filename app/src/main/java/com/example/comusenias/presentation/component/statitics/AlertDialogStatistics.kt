package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.specialist.home.Title
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.github.tehras.charts.bar.BarChartData.Bar
import java.util.Locale.ROOT

@Composable
fun AlertDialogStatistics(
    openDialog: Boolean = false,
    onDissmiss: () -> Unit,
    titleStatistics: String,
    statistic: List<Bar>
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onDissmiss()
            },
            title = {
                Title(title = titleStatistics)
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(SIZE5.dp)
                ) {
                    statistic.forEach {
                        Text(
                            text = "${it.label.uppercase(ROOT)} = ${it.value.toInt()}"
                        )
                    }
                }
            },
            confirmButton = {}
        )
    }
}
