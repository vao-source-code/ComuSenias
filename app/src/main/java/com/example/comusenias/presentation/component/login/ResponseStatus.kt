package com.example.comusenias.presentation.component.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.comusenias.constants.PreferencesConstant
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.users.Rol
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.example.comusenias.presentation.ui.theme.LOGIN_SUCCESS
import com.example.comusenias.presentation.view_model.LoginViewModel
import com.google.firebase.auth.FirebaseUser

@Composable
fun ResponseStatusLogin(
    navController: NavHostController, response: Response<FirebaseUser>?, viewModel: LoginViewModel
) {
    when (response) {
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
                        AppScreen.HomeScreen.route
                    }
                }

                navController.navigate(route = targetRoute) {
                    popUpTo(AppScreen.LoginScreen.route) {
                        inclusive = true
                    }
                }

            }
            Toast.makeText(LocalContext.current, LOGIN_SUCCESS, Toast.LENGTH_SHORT).show()
        }

        is Response.Error -> {
            Toast.makeText(
                LocalContext.current, response.exception?.message + LOGIN_ERROR, Toast.LENGTH_SHORT
            ).show()
        }

        else -> {}
    }
}