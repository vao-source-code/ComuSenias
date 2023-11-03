package com.example.comusenias.presentation.screen.qr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.component.addPatient.FieldWithIcon
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.view_model.ScanQRViewModel


@Composable
fun LectorQR(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ScanQRViewModel = hiltViewModel()
) {

    val state = viewModel.state


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE36.dp, start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ContentTopProfile(patient = state.details, viewModel = viewModel)
        Text(text = state.details)

        if (state.details.isNotEmpty()) {
            ButtonApp(
                titleButton = "Agregar",
                onClickButton = {
                    viewModel.updateChildrenbySpecialist()
                }
            )
        } else {
            ButtonApp(
                titleButton = "Scan QR",
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

            MiCard()
        } else {
            ImageProfile(userImage = viewModel.stateChildren.image)
            ChildData(patient = viewModel.stateChildren)
        }

    }
}


@Composable
fun ImageProfile(userImage: String?) {
    if (userImage != null) {
        AsyncImage(
            modifier = Modifier
                .size(SIZE100.dp)
                .clip(CircleShape),
            model = userImage,
            contentScale = ContentScale.Crop,
            contentDescription = SELECTED_IMAGE,
            error = painterResource(id = R.drawable.profile_avatar)
        )
    }
}

@Composable
fun ChildData(patient: ChildrenModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE12.dp)
    ) {
        FieldWithIcon(
            icon = painterResource(R.drawable.baseline_calendar_month_24),
            text = patient.date
        )
        FieldWithIcon(
            icon = painterResource(R.drawable.phone_icon),
            text = patient.tel
        )
        FieldWithIcon(
            icon = painterResource(R.drawable.mail_icon),
            text = patient.email
        )
    }
}

@Composable
fun MiCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    text = "Aprieta el botón QR",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = "Al apretar el botón QR debe escanear el QR del niño",
                style = MaterialTheme.typography.body1
            )
        }
    }
}
