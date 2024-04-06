package com.ars.comusenias.presentation.component.statitics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.R.drawable.correct_image
import com.ars.comusenias.R.drawable.game
import com.ars.comusenias.R.drawable.incorrect_image
import com.ars.comusenias.presentation.component.specialist.home.Title
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.ui.theme.SIZE24
import com.ars.comusenias.presentation.ui.theme.SIZE5
import com.ars.comusenias.presentation.ui.theme.blackColorApp
import com.ars.comusenias.presentation.ui.theme.greenColorApp
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
                    textAlign = TextAlign.Center
                    )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = spacedBy(SIZE5.dp),
                    horizontalAlignment = CenterHorizontally
                ) {
                    statistic.forEach {
                            StatisticDetailComponent(
                                text = it.label.uppercase(ROOT),
                                value = it.value.toInt(),
                                color = it.color)
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
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = SIZE24.sp,
                    fontWeight = Medium,
                    color = blackColorApp ,
                    textAlign = Start
                )
            )
            Spacer(modifier = Modifier.width(SIZE10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = End,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = value.toString(),
                    style = TextStyle(
                        fontSize = SIZE24.sp,
                        fontWeight = Medium,
                        color = blackColorApp
                    )
                )
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = EMPTY_STRING,
                    modifier = Modifier.height(SIZE24.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        Divider(modifier = Modifier.height(SIZE2.dp))
    }
}

