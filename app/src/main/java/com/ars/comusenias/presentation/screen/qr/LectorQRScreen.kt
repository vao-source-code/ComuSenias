package com.ars.comusenias.presentation.screen.qr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.component.defaults.CardInfoDefault
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.component.specialist.profilePatient.ChildData
import com.ars.comusenias.presentation.component.specialist.profilePatient.ImageProfile
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.ERROR_LECTOR_QR
import com.ars.comusenias.presentation.ui.theme.PRESS_THE_QR_BUTTON
import com.ars.comusenias.presentation.ui.theme.QR_ADD
import com.ars.comusenias.presentation.ui.theme.QR_SCAN
import com.ars.comusenias.presentation.ui.theme.QR_SCAN_TEXT_CHILDREN
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE36
import com.ars.comusenias.presentation.view_model.specialist.ScanQRViewModel


@Composable
fun LectorQRScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ScanQRViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = SIZE36.dp, start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContentTopProfile(patient = state.details, viewModel = viewModel)
        if (state.details.isNotEmpty()) {
            ButtonApp(titleButton = QR_ADD, onClickButton = {
                viewModel.updateChildrenBySpecialist()
            })
        } else {
            ButtonApp(titleButton = QR_SCAN, onClickButton = {
                viewModel.startScanning()
            })
        }

    }
    ResponseStatusLectorScreen(navController = navController, viewModel = viewModel)
}

@Composable
fun ResponseStatusLectorScreen(
    navController: NavHostController, viewModel: ScanQRViewModel
) {
    when (val observationResponse = viewModel.addChildrenResponse) {
        is Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(AppScreen.SpecialistScreen.route) {
                    popUpTo(AppScreen.SpecialistScreen.route) {
                        inclusive = true
                    }
                }

            }
        }

        is Response.Error -> {
            ToastMake.showError(
                LocalContext.current,
                ERROR_LECTOR_QR
            )
        }

        else -> {}
    }
}

@Composable
fun ContentTopProfile(
    patient: String, viewModel: ScanQRViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SIZE20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (patient.isEmpty()) {
            CardInfoDefault(
                title = PRESS_THE_QR_BUTTON, description = QR_SCAN_TEXT_CHILDREN
            )
        } else {
            ImageProfile(userImage = viewModel.stateChildren.image)
            ChildData(patient = viewModel.stateChildren)
        }

    }
}


