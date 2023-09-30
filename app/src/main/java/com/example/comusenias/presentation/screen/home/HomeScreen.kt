package com.example.comusenias.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.example.comusenias.presentation.component.home.ContentHome
import com.example.comusenias.presentation.component.home.TopBarHome
import com.example.comusenias.presentation.ui.theme.size3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = size3.dp) {
                TopBarHome()
            }
        },
        bottomBar = {
            ShowBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize() // Ocupar todo el espacio disponible
                .padding(paddingValues)
        ) {
            ContentHome(
                navController = navController,
                modifier = Modifier.fillMaxSize() // Ocupar todo el espacio disponible en la caja
            )
        }
    }
}
