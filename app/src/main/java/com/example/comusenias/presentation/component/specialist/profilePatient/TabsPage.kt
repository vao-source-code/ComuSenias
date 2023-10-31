package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.size14

@Composable
fun TabsPage(
    tabContent: List<@Composable () -> Unit>
) {
    val titles = listOf("Progreso", "Observaciones")
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
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .padding(horizontal = size14.dp),
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
            fontWeight = FontWeight.Bold,
            color = color
        )
    )
}


