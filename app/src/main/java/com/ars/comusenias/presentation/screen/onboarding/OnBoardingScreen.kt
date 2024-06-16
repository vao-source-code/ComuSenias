package com.ars.comusenias.presentation.screen.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ars.comusenias.presentation.component.onboarding.OnBoarding
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController,modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        OnBoarding(navController = navController)
    }
}