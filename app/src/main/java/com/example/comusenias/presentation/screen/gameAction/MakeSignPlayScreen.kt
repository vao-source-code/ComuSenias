package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.DESC_IMAGE_SIGN
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE390
import com.example.comusenias.presentation.ui.theme.SIZE60

@Composable
fun MakeSignPlayScreen(navController: NavHostController, modifier: Modifier) {
    MakeSignPlayView(navController = navController, modifier = modifier)
}

@Composable
fun MakeSignPlayView(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier
                    .size(SIZE390.dp)
                    .padding(top = SIZE16.dp),
                painter = painterResource(id = R.drawable.ia_o_poc),
                contentDescription = DESC_IMAGE_SIGN
            )
            Box(
                modifier = Modifier
                    .padding(bottom = SIZE100.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                ButtonApp(
                    titleButton = CONTINUE,
                    onClickButton = { navController.navigate(AppScreen.CongratsPlayScreen.route) },
                    modifier = modifier.padding(top = SIZE60.dp, start = SIZE16.dp, end = SIZE16.dp)
                )
            }
        }
    }
}