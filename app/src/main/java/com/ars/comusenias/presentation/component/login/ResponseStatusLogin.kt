package com.ars.comusenias.presentation.component.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.models.response.ErrorCode
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.view_model.LoginViewModel

@Composable
fun ResponseStatusLogin(
    navController: NavHostController, viewModel: LoginViewModel
) {

    when (viewModel.loginResponse) {
        Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.2f))
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
        }

        is Response.ErrorFirebase -> {
            val errorCode = (viewModel.loginResponse as Response.ErrorFirebase).errorCode.toString()

            Log.e(
                "Error",
                errorCode
            )

            ToastMake.showError(
                LocalContext.current,
                ErrorCode.getErrorMessage(errorCode)
            )
        }

        else -> {}
    }
}