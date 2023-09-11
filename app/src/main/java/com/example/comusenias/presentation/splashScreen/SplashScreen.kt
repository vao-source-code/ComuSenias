package com.example.comusenias.presentation.splashScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.GetImage
import com.example.comusenias.presentation.component.defaults.GetTextSplash
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.logoApp
import com.example.comusenias.presentation.ui.theme.size100
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(5000)
        navController.popBackStack()
        navController.navigate(AppScreen.OnBoardingScreen.route)
    }
    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        GetImage()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SetImage()
        GetTextSplash()
    }

}

@Composable
fun GetImage() {
    Image(
        painter = painterResource(id = R.drawable.fondo),
        contentDescription = "Imagen de fondo",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun SetImage() {
    GetImage(
        painter = R.drawable.lenguaje_de_senas_1,
        contentDescription = logoApp,
        width = size100.dp,
        height = size100.dp
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}