package com.example.comusenias.presentation.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.comusenias.presentation.component.onboarding.OnBoarding
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingScreen() {
            OnBoarding()
}