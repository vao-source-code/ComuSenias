package com.example.comusenias.presentation.screen.specialist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.specialist.SpecialistHomeContent

@Composable
fun SpecialistScreen(navController: NavHostController, modifier: Modifier) {
    SpecialistHomeContent(
        navController = navController, modifier = modifier
    )
}