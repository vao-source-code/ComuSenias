package com.example.comusenias.presentation.component.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.ToastMake
import com.example.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.example.comusenias.presentation.view_model.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun ResponseStatusLogin(
    navController: NavHostController, viewModel: LoginViewModel
) {

    when (viewModel.loginResponse) {
        Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                DefaultLoadingProgressIndicator()

            }
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.initRol()
                delay(2000)
            }
        }

        is Response.Error -> {
            Log.e(
                "Error",
                (viewModel.loginResponse as Response.Error).exception?.message.toString()
            )
            ToastMake.showError(
                LocalContext.current,
                LOGIN_ERROR
            )
        }

        else -> {}
    }
}