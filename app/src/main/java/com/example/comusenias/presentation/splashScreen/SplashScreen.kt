package com.example.comusenias.presentation.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.constants.TestTag.Companion.TAG_BOX_SPLASH_SCREEN
import com.example.comusenias.constants.TestTag.Companion.TAG_COLUMN_SPLASH_SCREEN
import com.example.comusenias.presentation.component.defaults.GetImage
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.SIZE150
import com.example.comusenias.presentation.ui.theme.logoApp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.popBackStack()
        navController.navigate(AppScreen.LoginScreen.route)
    }
    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .testTag(TAG_BOX_SPLASH_SCREEN)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TAG_COLUMN_SPLASH_SCREEN),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SetImageSplash()
    }
}

@Composable
private fun SetImageSplash() {
    GetImage(
        painter = R.drawable.comu_senias_with_text,
        contentDescription = logoApp,
        width = SIZE150.dp,
        height = SIZE150.dp
    )
}