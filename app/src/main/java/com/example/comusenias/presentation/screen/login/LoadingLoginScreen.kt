package com.example.comusenias.presentation.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.login.ResponseStatusLogin
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun LoadingLoginScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        ResponseStatusLogin(
            navController = navController,
            viewModel = viewModel
        )
    }
}
