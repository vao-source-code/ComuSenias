package com.example.comusenias.presentation.screen.qr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.presentation.component.defaults.CardInfoDefault
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.specialist.profilePatient.ChildData
import com.example.comusenias.presentation.component.specialist.profilePatient.ImageProfile
import com.example.comusenias.presentation.ui.theme.PRESS_THE_QR_BUTTON
import com.example.comusenias.presentation.ui.theme.QR_ADD
import com.example.comusenias.presentation.ui.theme.QR_SCAN
import com.example.comusenias.presentation.ui.theme.QR_SCAN_TEXT_CHILDREN
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.view_model.specialist.ScanQRViewModel


@Composable
fun LectorQRScreen(
    navController: NavController,
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
        Text(text = state.details)

        if (state.details.isNotEmpty()) {
            ButtonApp(
                titleButton = QR_ADD,
                onClickButton = {
                    viewModel.updateChildrenBySpecialist()
                }
            )
        } else {
            ButtonApp(
                titleButton = QR_SCAN,
                onClickButton = {
                    viewModel.startScanning()
                }
            )
        }

    }

}

@Composable
fun ContentTopProfile(
    patient: String,
    viewModel: ScanQRViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SIZE20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (patient.isEmpty()) {
            CardInfoDefault(
                title = PRESS_THE_QR_BUTTON,
                description = QR_SCAN_TEXT_CHILDREN
            )
        } else {
            ImageProfile(userImage = viewModel.stateChildren.image)
            ChildData(patient = viewModel.stateChildren)
        }

    }
}


