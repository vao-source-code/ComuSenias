package com.example.comusenias.presentation.component.login

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
import com.example.comusenias.presentation.navigation.AuthScreen
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

                when (viewModel.dataRolStorageFactory.getRolValue(PreferencesConstant.PREFERENCE_ROL_CURRENT)) {
                    null -> {
                        viewModel.initRol()
                        delay(2000)
                        navController.navigate(route = AuthScreen.LoadingScreen.route) {
                        }
                    }

                    Rol.SPECIALIST.toString() -> {
                        navController.navigate(route = AppScreen.SpecialistScreen.route) {
                            popUpTo(AppScreen.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }

                    Rol.CHILDREN.toString() -> {
                        navController.navigate(route = AppScreen.HomeScreen.route) {
                            popUpTo(AppScreen.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }

                    else -> {
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

        else -> {}
    }
}