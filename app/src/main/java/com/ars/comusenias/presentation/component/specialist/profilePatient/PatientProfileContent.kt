package com.ars.comusenias.presentation.component.specialist.profilePatient

import android.util.Log
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.screen.specialist.StatisticsScreen
import com.ars.comusenias.presentation.ui.theme.OBSERVATIONS_ERROR
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.view_model.specialist.ProfilePatientViewModel

@Composable
fun PatientProfileContent(
    navController: NavHostController,
    patient: ChildrenModel,
    viewModel: ProfilePatientViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ContentTopProfile(patient = patient)
        TabsPage(
            tabContent = listOf(
                {
                    StatisticsScreen(navController = navController , viewModel = viewModel)
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
            .padding(top = SIZE20.dp, start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE20.dp),
        horizontalArrangement = spacedBy(SIZE20.dp),
        verticalAlignment = CenterVertically
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
            Log.e("Error", observationResponse.exception?.message.toString())
            ToastMake.showError(
                LocalContext.current,
                OBSERVATIONS_ERROR
            )
        }

        else -> {}
    }
}


