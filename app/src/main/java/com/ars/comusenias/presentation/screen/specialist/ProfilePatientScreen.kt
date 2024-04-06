package com.ars.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.presentation.component.defaults.DefaultTopBar
import com.ars.comusenias.presentation.component.specialist.profilePatient.PatientProfileContent
import com.ars.comusenias.presentation.ui.theme.SIZE3
import com.ars.comusenias.presentation.view_model.specialist.ProfilePatientViewModel

@Composable
fun ProfilePatientScreen(
    navController: NavHostController,
    modifier: Modifier,
    pacient: String,
    viewModel: ProfilePatientViewModel = hiltViewModel()
) {

    val patient = viewModel.user

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Surface(shadowElevation = SIZE3.dp) {
                    DefaultTopBar(
                        title = patient.name,
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
                PatientProfileContent(
                    navController = navController,
                    patient = patient,
                    viewModel = viewModel
                )
            }
        }
    }
}
