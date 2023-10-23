package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.ui.theme.OBSERVATION
import com.example.comusenias.presentation.ui.theme.SEND
import com.example.comusenias.presentation.ui.theme.SIZE3
import com.example.comusenias.presentation.ui.theme.size20

@Composable
fun SendObservationScreen(navController: NavHostController, modifier: Modifier) {
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
                    .padding(size20.dp)
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
        ) {
        }
    }
}