package com.example.comusenias.presentation.screen.premiun

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.bottomBar.ShowBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    Scaffold(
        bottomBar = {
            ShowBottomBar(navController = navController)
        },
    ) { paddingValues ->

        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.premium),
                contentDescription = "premium screen",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}