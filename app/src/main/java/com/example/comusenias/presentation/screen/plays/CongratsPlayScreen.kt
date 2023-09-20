package com.example.comusenias.presentation.screen.plays

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.comusenias.presentation.ui.theme.FINISH_LVL
import com.example.comusenias.presentation.ui.theme.GO_FOR_MORE
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE250
import com.example.comusenias.presentation.ui.theme.SIZE60
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun CongratsPlayScreen(navController: NavHostController, modifier: Modifier) {
    CongratsPlayView(navController = navController, modifier)
}

@Composable
fun CongratsPlayView(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier.padding(top = SIZE60.dp),
                text = FINISH_LVL,
                color = primaryColorApp,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = GO_FOR_MORE,
                color = primaryColorApp,
                style = MaterialTheme.typography.h6,
            )
            Image(
                modifier = modifier
                    .size(SIZE250.dp)
                    .padding(top = SIZE16.dp),
                painter = painterResource(id = R.drawable.check_congrats),
                contentDescription = DESC_IMAGE_SIGN
            )
            ButtonApp(
                titleButton = CONTINUE,
                onClickButton = { navController.navigate(AppScreen.HomeScreen.route) },
                modifier = modifier.padding(top = SIZE60.dp, start = SIZE16.dp, end = SIZE16.dp)
            )
        }
    }
}
