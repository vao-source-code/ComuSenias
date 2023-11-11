package com.example.comusenias.presentation.component.specialist.profilePatient

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.ToastMake
import com.example.comusenias.presentation.screen.specialist.StatisticsScreen
import com.example.comusenias.presentation.ui.theme.OBSERVATIONS_ERROR
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.view_model.specialist.ProfilePatientViewModel

@Composable
fun PatientProfileContent(
    navController: NavHostController,
    patient: ChildrenModel,
    viewModel: ProfilePatientViewModel
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ContentTopProfile(patient = patient)
        TabsPage(
            tabContent = listOf(
                {
                    StatisticsScreen()
                },
                {
                    ResponseStatusObservationScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            )
        )
    }
}

@Composable
fun ContentTopProfile(
    patient: ChildrenModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE36.dp, start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE20.dp),
        horizontalArrangement = Arrangement.spacedBy(SIZE20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageProfile(userImage = patient.image)
        ChildData(patient = patient)
    }
}

@Composable
fun ResponseStatusObservationScreen(
    navController: NavHostController,
    viewModel: ProfilePatientViewModel
) {
    when (val observationResponse = viewModel.observationResponse) {
        is Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            Log.i("Observation", observationResponse.data.toString())
            ObservationsScreen(
                observations = observationResponse.data,
                navController = navController,
                viewModel = viewModel
            )
        }

        is Response.Error -> {
            Log.e("Error", (observationResponse as Response.Error).exception?.message.toString())
            ToastMake.showError(
                LocalContext.current,
                OBSERVATIONS_ERROR
            )
        }

        else -> {}
    }
}


