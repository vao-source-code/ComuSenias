package com.ars.comusenias.presentation.component.statitics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.R.drawable.info
import com.ars.comusenias.presentation.extensions.statitics.StatisticsCalculator.sumStatistics
import com.ars.comusenias.presentation.ui.theme.CHOICES
import com.ars.comusenias.presentation.ui.theme.CORRECT
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.INCORRECT
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE5
import com.ars.comusenias.presentation.ui.theme.blackColorApp
import com.ars.comusenias.presentation.ui.theme.greenColorApp
import com.ars.comusenias.presentation.ui.theme.primaryColorApp
import com.github.tehras.charts.bar.BarChartData.Bar

@Composable
fun StatisticsDescriptions(
    statisticsSuccess: List<Bar>,
    statisticsFailures: List<Bar>,
    statisticsTotal: List<Bar>,
) {

    var showInfo by remember {
        mutableStateOf(false)
    }
    var titleInfo by remember {
        mutableStateOf("")
    }

    var statistic by remember {
        mutableStateOf(statisticsSuccess)
    }


    Column(
        verticalArrangement = spacedBy(SIZE5.dp)
    ) {
        ContentStatisticDescription(
            color = primaryColorApp,
            value = sumStatistics(statisticsTotal),
            description = CHOICES,
            click = {
                titleInfo = CHOICES
                statistic = statisticsTotal
                showInfo = true
            }
        )
        ContentStatisticDescription(
            color = greenColorApp,
            value = sumStatistics(statisticsSuccess),
            description = CORRECT,
            click = {
                titleInfo = CORRECT
                statistic = statisticsSuccess
                showInfo = true
            }
        )
        ContentStatisticDescription(
            color = Red,
            value = sumStatistics(statisticsFailures),
            description = INCORRECT,
            click = {
                titleInfo = INCORRECT
                statistic = statisticsFailures
                showInfo = true
            }
        )
        AlertDialogStatistics(
            openDialog = showInfo,
            onDissmiss = { showInfo = false },
            titleStatistics = titleInfo,
            statistic = statistic
        )
    }
}

@Composable
fun ContentStatisticDescription(
    color: Color,
    value: Int,
    description: String,
    click: () -> Unit = {}
) {
    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = spacedBy(SIZE5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(SIZE14.dp)
                .background(color)
        )
        Text(
            text = "${value} ${description}",
            style = TextStyle(
                fontSize = SIZE14.sp,
                fontWeight = SemiBold,
                color = blackColorApp,
            )
        )
            Icon(
                modifier = Modifier
                    .size(SIZE16.dp)
                    .clickable { click() },
                painter = painterResource(id = info),
                contentDescription = EMPTY_STRING,
                tint = Gray
            )
    }
}
