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
import com.example.comusenias.presentation.component.specialist.profilePatient.PatientProfileContent
import com.example.comusenias.presentation.ui.theme.OBSERVATION
import com.example.comusenias.presentation.ui.theme.SIZE3

@Composable
fun ProfilePatientScreen(navController: NavHostController, modifier: Modifier) {

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Surface(shadowElevation = SIZE3.dp) {
                    DefaultTopBar(
                        title = OBSERVATION,
                        upAvailable = true,
                        navHostController = navController
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                PatientProfileContent(navController = navController)
            }
        }
    }
}
