package com.example.comusenias.presentation.component.statitics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.R.drawable.correct_image
import com.example.comusenias.R.drawable.game
import com.example.comusenias.R.drawable.incorrect_image
import com.example.comusenias.presentation.component.specialist.home.Title
import com.example.comusenias.presentation.ui.theme.CORRECT
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.example.comusenias.presentation.ui.theme.greenColorApp
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
                Title(
                    title = titleStatistics,
                    textAlign = Center
                    )
            },
            text = {
                Column(
                    verticalArrangement = spacedBy(SIZE5.dp),
                    horizontalAlignment = CenterHorizontally
                ) {
                    statistic.forEach {
                            StatisticDetailComponent(
                                text = it.label.uppercase(ROOT),
                                value = it.value.toInt(),
                                color = it.color)
                        Divider(modifier = Modifier.height(SIZE2.dp))
                    }
                }
            },
            confirmButton = {}
        )
    }
}

@Composable
fun StatisticDetailComponent(
    text: String,
    value: Int,
    color: Color
) {

    val imageResource = when (color) {
        Red -> incorrect_image
        greenColorApp -> correct_image
        else -> game
    }

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(SIZE10.dp)
    ) {
        Title(title = text)
        Row (
            horizontalArrangement = End
        ) {
            Title(title = value.toString())
            Image(
                painter = painterResource(id = imageResource ),
                contentDescription = EMPTY_STRING,
                modifier = Modifier.height(SIZE24.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

val vowels = listOf(
    Bar(5f, greenColorApp, "A"),
    Bar(5f, greenColorApp, "A"),
    Bar(5f, greenColorApp, "A"),
    Bar(5f, greenColorApp, "A"),
    Bar(5f, greenColorApp, "A")
)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AlertDialogStatisticsShow() {
    AlertDialogStatistics(openDialog = true,onDissmiss = { }, titleStatistics = CORRECT, statistic = vowels )
}

