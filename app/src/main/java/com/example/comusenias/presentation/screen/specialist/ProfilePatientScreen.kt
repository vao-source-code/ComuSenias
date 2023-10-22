package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.specialist.PatientProfileContent

@Composable
fun ProfilePatientScreen(navController: NavHostController, modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        PatientProfileContent()
    }
}
