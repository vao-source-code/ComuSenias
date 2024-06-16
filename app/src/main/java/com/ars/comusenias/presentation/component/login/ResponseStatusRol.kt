package com.ars.comusenias.presentation.component.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ars.comusenias.constants.PreferencesConstant
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.Rol
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.ars.comusenias.presentation.view_model.LoginViewModel
import es.dmoral.toasty.Toasty

@Composable
fun ResponseStatusRol(
    navController: NavHostController, viewModel: LoginViewModel
) {

    when (viewModel.userResponse) {
        Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                DefaultLoadingProgressIndicator()

            }
        }

        is Response.Success -> {
            Toasty.success(LocalContext.current, "Success!", Toast.LENGTH_SHORT, true).show();

            LaunchedEffect(Unit) {

                when (viewModel.dataRolStorageFactory.getRolValue(PreferencesConstant.PREFERENCE_ROL_CURRENT)) {
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