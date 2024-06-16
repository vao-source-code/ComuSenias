package com.ars.comusenias.presentation.component.register.especialistForm

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.REGISTER_ERROR
import com.ars.comusenias.presentation.view_model.specialist.SpecialistRegisterViewModel

@Composable
fun ResponseStatusChildrenRegister(
    navController: NavHostController,
    viewModel: SpecialistRegisterViewModel = hiltViewModel()
) {
    when (val registerResponse = viewModel.registerResponse) {
        is Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                navController.popBackStack(AppScreen.LoginScreen.route, inclusive = true)
                navController.navigate(route = AppScreen.SpecialistScreen.route)
            }
        }

        is Response.Error -> {
            Log.e(
                "Error",
                (viewModel.registerResponse as Response.Error).exception?.message.toString()
            )
            ToastMake.showError(
                LocalContext.current,
                REGISTER_ERROR
            )
        }

        else -> {}
    }
}
