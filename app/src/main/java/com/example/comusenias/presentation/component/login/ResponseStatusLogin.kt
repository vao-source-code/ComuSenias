package com.example.comusenias.presentation.component.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun ResponseStatusLogin(navController : NavHostController , viewModel: LoginViewModel = hiltViewModel()) {
    when (val loginResponse = viewModel.loginResponse) {
        Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                DefaultLoadingProgressIndicator()
            }
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = AppScreen.HomeScreen.route) {
                    popUpTo(AppScreen.LoginScreen.route) {
                        inclusive = true
                    }
                }

            }
            Toast.makeText(LocalContext.current, "Login Success", Toast.LENGTH_SHORT)
                .show()
        }

        is Response.Error -> {
            Toast.makeText(
                LocalContext.current,
                loginResponse.exception?.message + "Login Error",
                Toast.LENGTH_SHORT
            ).show()

        }

        else -> {}
    }
}