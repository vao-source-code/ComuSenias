package com.example.comusenias.presentation.screen.qr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.ui.theme.SIZE10
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
            .padding(SIZE10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = state.details)

        ButtonDefault(
            text = "Lector QR",
            icon = Icons.Default.Create,
            onClick = { viewModel.startScanning() })
    }

}