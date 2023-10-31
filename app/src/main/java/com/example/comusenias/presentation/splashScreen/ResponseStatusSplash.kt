package com.example.comusenias.presentation.splashScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.comusenias.constants.PreferencesConstant
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.Rol
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun ResponseStatusSplash(
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
                val targetRoute = when (viewModel.dataRolStorageFactory.getRolValue(
                    PreferencesConstant.PREFERENCE_ROL_CURRENT
                )) {
                    Rol.CHILDREN.toString() -> {
                        AppScreen.HomeScreen.route
                    }

                    Rol.SPECIALIST.toString() -> {
                        AppScreen.SpecialistScreen.route
                    }

                    else -> {
                        AppScreen.LoginScreen.route
                    }
                }

                navController.navigate(route = targetRoute) {
                    popUpTo(AppScreen.SplashScreen.route) {
                        inclusive = true
                    }
                }

            }
        }

        is Response.Error -> {
            Toast.makeText(
                LocalContext.current,
                (viewModel.loginResponse as Response.Error).exception?.message + LOGIN_ERROR,
                Toast.LENGTH_SHORT
            ).show()
        }

        else -> {

            navController.navigate(
                route = AppScreen.LoginScreen.route
            ) {
                popUpTo(AppScreen.SplashScreen.route) {
                    inclusive = true
                }
            }
        }
    }
}