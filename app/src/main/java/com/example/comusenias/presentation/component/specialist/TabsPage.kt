package com.example.comusenias.presentation.component.specialist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.cardGray
import com.example.comusenias.presentation.ui.theme.greenColorApp

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TabsPage(
    content: @Composable () -> Unit = {}
) {
    val titles = listOf("Perfil", "Progreso")
    var tabIndex by remember { mutableStateOf(0) }
    var select by remember { mutableStateOf(true) }


    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = tabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            TiltePage(
                                color = blackColorApp,
                                title = title
                            )
                        },
                        selectedContentColor = greenColorApp,
                        unselectedContentColor = cardGray,
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
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
        text = title,
        style = TextStyle(
            fontSize = SIZE24.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    )
}


