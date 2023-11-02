package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.specialist.observation.CustomMultilineHintTextField
import com.example.comusenias.presentation.ui.theme.OBSERVATION
import com.example.comusenias.presentation.ui.theme.SEND
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE3
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun SendObservationScreen(navController: NavHostController, modifier: Modifier) {
    var text by remember {
        mutableStateOf("")
    }

    var letters by remember {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            Surface(shadowElevation = SIZE3.dp) {
                DefaultTopBar(
                    title = OBSERVATION,
                    upAvailable = true,
                    navHostController = navController
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(SIZE20.dp)
            ) {
                ButtonApp(
                    titleButton = SEND,
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .padding(top = SIZE40.dp, start = SIZE20.dp, end = SIZE20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SIZE1.dp)
            ) {
                CustomMultilineHintTextField(
                    value = text,
                    onValueChanged = {
                        letters = it.length
                        if (it.length <= 230) {
                            text = it
                        }
                    }
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${letters}/230",
                    style = TextStyle(
                        fontSize = SIZE12.sp,
                        fontWeight = FontWeight.Normal,
                        color = blackColorApp,
                        textAlign = TextAlign.Right
                    )
                )
            }

        }
    }
}