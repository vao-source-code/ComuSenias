package com.example.comusenias.presentation.screen.qr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.presentation.component.defaults.CardInfoDefault
import com.example.comusenias.presentation.ui.theme.AVATAR
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE350
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.view_model.GenerateQRViewModel

@Composable
fun GenerateQRScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GenerateQRViewModel = hiltViewModel()
) {
    val state = viewModel.state
    ContentTopProfile(patient = state.details, viewModel = viewModel)
}

@Composable
fun ContentTopProfile(
    patient: String, viewModel: GenerateQRViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE36.dp, start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (viewModel.state.bitmap != null) {
            Image(
                modifier = Modifier.size(SIZE350.dp),
                bitmap = viewModel.state.bitmap!!.asImageBitmap(),
                contentScale = ContentScale.Fit,
                contentDescription = AVATAR
            )
        } else {
            Text(
                text = "Loading...",
                color = blackColorApp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
        CardInfoDefault(
            title = "QR para escanear",
            description = "Utilize el qr para vincularse con el especialista"
        )
    }
}
