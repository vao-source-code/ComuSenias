package com.example.comusenias.presentation.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.comusenias.presentation.component.onboarding.OnBoarding
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
            OnBoarding(navController = navController)
}