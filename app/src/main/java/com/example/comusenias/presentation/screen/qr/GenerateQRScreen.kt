package com.example.comusenias.presentation.screen.qr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.presentation.ui.theme.AVATAR
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.view_model.GenerateQRViewModel

@Composable
fun GenerateQRScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GenerateQRViewModel = hiltViewModel()
) {

    val state = viewModel.state

    if (viewModel.state.bitmap != null) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
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
}