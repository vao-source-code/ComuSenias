package com.example.comusenias.presentation.component.register.especialistForm

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.app.showToast
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.specialist.SpecialistRegisterViewModel

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
            showToast(
                LocalContext.current,
                registerResponse.exception?.message.toString(),
                Toast.LENGTH_SHORT
            )
        }

        else -> {}
    }
}
