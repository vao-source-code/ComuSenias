package com.ars.comusenias.presentation.screen.gameAction

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants.IterateForever
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ars.comusenias.R.raw.loading
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.INFO_TEXT_OPEN_CAMERA
import com.ars.comusenias.presentation.ui.theme.SIZE24
import com.ars.comusenias.presentation.ui.theme.blackColorApp
import kotlinx.coroutines.delay

@Composable
fun InfoMakeSignScreen(navController: NavController) {
    val loadingImage by rememberLottieComposition(LottieCompositionSpec.RawRes(loading))

    LaunchedEffect(Unit) {
        delay(6000)
        if (getLevelViewModel.isVideo) {
           // navController.navigate(AppScreen.RecordCameraScreen.route)
            navController.navigate(AppScreen.PermissioRecordCameraScreen.route)

        } else {
            navController.navigate(AppScreen.CameraScreen.route)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Center
    ) {
        Column {
            LottieAnimation(
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.Center,
                composition = loadingImage,
                iterations = IterateForever
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SIZE24.dp),
                text = INFO_TEXT_OPEN_CAMERA,
                style = TextStyle(
                    fontSize = SIZE24.sp,
                    fontWeight = SemiBold,
                    color = blackColorApp,
                    textAlign = TextAlign.Center
                ),
            )
        }
    }
}