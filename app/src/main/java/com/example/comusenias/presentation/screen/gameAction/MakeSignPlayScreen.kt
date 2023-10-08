package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import kotlinx.coroutines.delay

@Composable
fun MakeSignPlayScreen(navController: NavHostController, modifier: Modifier) {

    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(AppScreen.InterpretationStatusScreen.route)
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.image_sign),
            contentDescription = EMPTY_STRING,
            contentScale = ContentScale.FillBounds
        )
    }
}