package com.ars.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.presentation.ui.theme.OBSERVATIONS
import com.ars.comusenias.presentation.ui.theme.PROGRESS_GAME
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun TabsPage(
    tabContent: List<@Composable () -> Unit>
) {
    val titles = listOf(PROGRESS_GAME, OBSERVATIONS)
    var tabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = tabIndex
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    ) {
                        TiltePage(
                            color = blackColorApp,
                            title = title
                        )
                    }
                }
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = SIZE16.dp)
                    .fillMaxSize(),
            ) {
                tabContent[tabIndex]()
            }
        }
    )
}

@Composable
fun TiltePage(
    color: Color,
    title: String
) {
    Text(
        modifier = Modifier.padding(SIZE10.dp),
        text = title,
        style = TextStyle(
            fontSize = SIZE16.sp,
            fontWeight = Bold,
            color = color
        )
    )
}


